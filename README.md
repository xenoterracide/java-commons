<!--
SPDX-FileCopyrightText: Copyright © 2024-2026 Caleb Cushing

SPDX-License-Identifier: CC-BY-NC-SA-4.0
-->

# Contributing

## Languages

[asdf](https://asdf-vm.com) is suggested, you can use whatever you'd like to get

- Java (version in `.tool-versions`)
- Node.js (version in `.tool-versions`)

add a way to export these to your `PATH` in your `~/.profile`

## Build Tools

- [Apache Maven](https://maven.apache.org/)
- [pnpm](https://pnpm.io/installation) (via Corepack)

#### Fetching Dependencies

```sh
# Node stopped shipping Corepack at 25.0.0, and every script here runs through
# pnpm, which Corepack provides, so this is the one manual step
npm install --global corepack
corepack enable --install-directory ~/.local/bin
# Install the pinned pnpm, Node/Python deps, and git hooks
pnpm contribute
./mvnw verify
```

If you need the individual steps, they are:

```sh
pnpm install --frozen-lockfile
uv sync --frozen --group dev
git config core.hooksPath .config/git/hooks
```

## Committing

Use [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).

## AI Agent Skills

This repository uses skills from [xenoterracide/agent-skills](https://github.com/xenoterracide/agent-skills), a Kimi Code plugin providing AI coding agent capabilities for Java, Gradle, GitHub, and development workflows.

# License

Apache 2.0
