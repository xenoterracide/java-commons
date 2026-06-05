<!--
SPDX-FileCopyrightText: Copyright © 2024-2026 Caleb Cushing

SPDX-License-Identifier: CC-BY-NC-SA-4.0
-->

# Contributing

## Languages

[asdf](https://asdf-vm.com) is suggested, you can use whatever you'd like to get

- Java 21+
- NodeJs

add a way to export these to your `PATH` in your `~/.profile`

## Build Tools

- [Apache Maven](https://maven.apache.org/)
- [Yarn 4](https://yarnpkg.com/getting-started/install) (via Corepack)

#### Fetching Dependencies

```sh
# Enable Corepack, install Node dev tools, run postinstall, then verify Maven deps
corepack enable
yarn install --immutable
yarn contribute
./mvnw verify
```

If you need to run the postinstall step directly, you can recreate and use the Python lock file via uv:

```sh
# Sync Python environment from pyproject.toml
uv sync --frozen --group dev

# Then install and set up commit hooks
uv run --frozen --group dev git config core.hooksPath .config/git/hooks
```

## Committing

Use [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).

## AI Agent Skills

This repository uses skills from [xenoterracide/agent-skills](https://github.com/xenoterracide/agent-skills), a Kimi Code plugin providing AI coding agent capabilities for Java, Gradle, GitHub, and development workflows.

# License

Apache 2.0
