<!--
SPDX-FileCopyrightText: Copyright © 2026 Caleb Cushing

SPDX-License-Identifier: CC-BY-NC-SA-4.0
-->

# pnpm migration — design

Migrate java-commons from yarn 4 (Plug'n'Play) to pnpm 12.16.1, the
migration github landed in #193 and agent-skills in #196.

## Why

The shared workflows from xenoterracide/github have been pnpm-based
since ead8563. PR 645 repins them to 0581f01 and its prettier and
devtool-regression checks fail against this yarn repo; they stay red
until java-commons runs pnpm. Migrating and repinning in one PR turns
them green and supersedes PR 645, which renovate then closes.

## Goals

- pnpm 12.16.1 via the `packageManager` pin; corepack shims in
  `~/.local/bin` serve it locally, `pnpm/action-setup` in CI.
- Lockfile from `pnpm import`; `pnpm install --frozen-lockfile` passes.
- Git hooks run pnpm; `.share` remnants and dead scripts removed.
- CI green on the 0581f01 workflows.

## Non-goals

Dependency bumps (renovate owns them), node or java version changes,
Maven changes, migrations in other repos.

## Anti-goals

Leftover `yarn.lock` or PnP files — pnpm 12 preloads a leftover
`.pnp.cjs` into `pnpm exec` and `pnpm run`, so deleting them is
required. A red window on develop: the repin rides in the migration
commit.

## Approaches

- **A — github pattern (chosen):** move post-checkout and post-merge
  into `.config/git/hooks`, convert all four hooks to pnpm, delete
  `.share/`, drop the dead `merge:*`, `test`, and `setup:yarn`
  scripts, repoint `contribute` and `.envrc` to `.config/git/hooks`.
  Matches the local `core.hooksPath` and the `.lintstagedrc.cjs` glob.
- **B — agent-skills pattern:** keep `.share/git/hooks` in place.
  Rejected: leaves a one-directory subtree remnant that lint-staged and
  prettier already skip.
- **C — minimal:** convert both hook sets, keep duplicates and dead
  scripts. Rejected: keeps the mess.

## Decisions

- One commit, `chore: migrate to pnpm`, carrying the workflow repin
  `75d7980 → 0581f01` so CI never mixes package managers. PR 645 closes
  itself once develop carries the new digest.
- `.envrc` follows agent-skills #207: self-healing — corepack into
  `~/.local/bin`, `pnpm install --frozen-lockfile`, `uv sync --frozen`,
  `git config core.hooksPath .config/git/hooks`.
- `renovate.json5` needs no change; #644 already added lock file
  maintenance.

## Left to the builder

File-by-file changes per github ead8563 and agent-skills b826cb2:
`package.json` scripts, hooks, `.gitignore`, `.gitattributes`,
`.prettierignore`, `.lintstagedrc.cjs` (`withoutYarn` →
`withoutGenerated` filtering `pnpm-lock.yaml`), `REUSE.toml` annotation
path, README and AGENTS prose, delete `yarn.lock` and the untracked PnP
artifacts, verify with `pnpm install --frozen-lockfile`, `pnpm lint`,
and a real commit through the hooks.
