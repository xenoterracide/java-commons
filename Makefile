# SPDX-FileCopyrightText: Copyright © 2025 Caleb Cushing
#
# SPDX-License-Identifier: MIT

HEAD := $(shell git rev-parse --verify HEAD)
TARGET_DIR := $(wildcard ./target/ */target/ ./module/*/target/)

.PHONY: build
build:
	./mvnw verify --batch-mode

.PHONY: clean
.clean:
	./mvnw clean

.PHONY: merge
merge: merge-head push create-pr build watch-full merge-squash

.PHONY: create-pr
push:
	git push

.PHONY: merge-head
merge-head:
	git fetch --all --prune --prune-tags --tags --force
	git merge origin/HEAD

.PHONY: merge-squash
merge-squash:
	gh pr merge --squash --delete-branch --auto

.PHONY: create-pr
create-pr:
	gh pr create --body "" || exit 0

.PHONY: watch
watch:
	@gh run watch $$($(call gh_head_run_id, "full")) --exit-status

.PHONY: watch-full
watch-full:
	@gh run watch $$($(call gh_head_run_id, "full")) --exit-status

define gh_head_run_id
	gh run list --workflow $(1) --commit $(HEAD) --json databaseId --jq '.[0]["databaseId"]'
endef
