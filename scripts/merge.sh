#!/usr/bin/env sh
# SPDX-FileCopyrightText: Copyright © 2026 Caleb Cushing
#
# SPDX-License-Identifier: MIT

set -e

repo_url=$(git remote get-url origin)
current_branch=$(git rev-parse --abbrev-ref HEAD)
origin_head=$(git remote show origin 2>/dev/null | awk '/HEAD branch/ {print $NF}')
[ -n "$origin_head" ] || origin_head=main

MR_IID=""
PIPELINE_WAIT_SECONDS=${PIPELINE_WAIT_SECONDS:-10}

glab_host=""
project_path=""

case "$repo_url" in
  *://*)
    url_no_scheme=${repo_url#*://}
    url_no_user=${url_no_scheme#*@}
    glab_host=${url_no_user%%/*}
    project_path=${url_no_user#*/}
    ;;
  *@*:* )
    url_no_user=${repo_url#*@}
    glab_host=${url_no_user%%:*}
    project_path=${url_no_user#*:}
    ;;
  *:* )
    glab_host=${repo_url%%:*}
    project_path=${repo_url#*:}
    ;;
  */* )
    glab_host=${repo_url%%/*}
    project_path=${repo_url#*/}
    ;;
  * )
    glab_host=""
    project_path=""
    ;;
esac

project_path=${project_path%.git}
project_path_enc=$(printf '%s\n' "$project_path" | sed 's#/#%2F#g')

glab_call() {
  if [ -n "$glab_host" ]; then
    GITLAB_HOST="$glab_host" glab "$@"
  else
    glab "$@"
  fi
}

glab_call_pager() {
  export GLAB_PAGER=cat
  glab_call "$@"
  unset GLAB_PAGER
}

wait_for_mr_pipelines() {
  if [ -z "$MR_IID" ]; then
    return 0
  fi

  glab_call_pager ci view || true

  while :; do
    pipelines_json=$(glab_call api "projects/$project_path_enc/merge_requests/$MR_IID/pipelines?per_page=1" 2>/dev/null || true)
    status=$(printf '%s\n' "$pipelines_json" | sed -n 's/.*"status":"\([^"]*\)".*/\1/p' | head -n 1)

    if [ -z "$status" ]; then
      printf '%s\n' "No pipelines found yet; waiting ${PIPELINE_WAIT_SECONDS}s..."
      sleep "$PIPELINE_WAIT_SECONDS"
      continue
    fi

    case "$status" in
      success|skipped)
        printf '%s\n' "Pipeline status: $status"
        return 0
        ;;
      failed|canceled)
        printf '%s\n' "Pipeline status: $status" 1>&2
        return 1
        ;;
      *)
        printf '%s\n' "Pipeline status: $status; waiting ${PIPELINE_WAIT_SECONDS}s..."
        sleep "$PIPELINE_WAIT_SECONDS"
        ;;
    esac
  done
}

create_or_update_mr() {
  tmp_dir=$(mktemp -d)
  trap 'rm -rf "$tmp_dir"' EXIT INT TERM

  head_before=$(git rev-parse HEAD)
  branch=$(git rev-parse --abbrev-ref HEAD)
  origin_head=$(git remote show origin 2>/dev/null | awk '/HEAD branch/ {print $NF}')
  [ -n "$origin_head" ] || origin_head=main

  mr_iid=$(glab_call api "projects/$project_path_enc/merge_requests?state=opened&source_branch=$branch" 2>/dev/null \
    | sed -n 's/.*"iid":\s*\([0-9][0-9]*\).*/\1/p' | head -n 1)

  generate_message() {
    ./scripts/pr-message.sh --title-file "$tmp_dir/title.txt" --body-file "$tmp_dir/body.txt" || return 1
    head_after=$(git rev-parse HEAD)
    if [ "$head_before" != "$head_after" ]; then
      ./scripts/pr-message.sh --title-file "$tmp_dir/title.txt" --body-file "$tmp_dir/body.txt" || return 1
    fi
    title=$(cat "$tmp_dir/title.txt")
    description=$(cat "$tmp_dir/body.txt")
  }

  if [ -n "$mr_iid" ]; then
    printf '%s\n' "Updating PR message..."
    if ! generate_message; then
      printf '%s\n' "PR message generation failed; continuing." 1>&2
      return 0
    fi
    glab_call api --method PUT "projects/$project_path_enc/merge_requests/$mr_iid" \
      -f "title=$title" -f "description=$description" > /dev/null || return 0
    glab_call_pager mr view "$branch" || true
    MR_IID=$mr_iid
  else
    if ! generate_message; then
      printf '%s\n' "PR message generation failed; continuing." 1>&2
      return 0
    fi
    mr_response=$(glab_call api --method POST "projects/$project_path_enc/merge_requests" \
      -f "title=$title" -f "description=$description" \
      -f "source_branch=$branch" -f "target_branch=$origin_head" || true)
    mr_iid=$(printf '%s\n' "$mr_response" | sed -n 's/.*"iid":\s*\([0-9][0-9]*\).*/\1/p' | head -n 1)
    [ -n "$mr_iid" ] || mr_iid=$(glab_call api "projects/$project_path_enc/merge_requests?state=opened&source_branch=$branch" 2>/dev/null \
      | sed -n 's/.*"iid":\s*\([0-9][0-9]*\).*/\1/p' | head -n 1)
    printf '%s\n' "PR created with generated message."
    glab_call_pager mr view "$branch" || true
    MR_IID=$mr_iid
  fi
}

git fetch --all --prune --prune-tags --tags --force
git merge origin/HEAD
git push

create_or_update_mr

./gradlew build --console=plain

wait_for_mr_pipelines

# Enable auto-merge with squash when pipelines pass.
glab_call mr merge --squash --remove-source-branch --auto-merge

# Mirror gh behavior: switch to head, update it, and delete merged branch locally.
if [ "$current_branch" != "$origin_head" ]; then
  git checkout "$origin_head"
  git pull --ff-only
  git branch -D "$current_branch" || true
else
  git pull --ff-only
fi
