<!--
<<<<<<< before updating
SPDX-FileCopyrightText: Copyright © 2024-2026 Caleb Cushing
=======
SPDX-FileCopyrightText: Copyright © 2015-2026 Caleb Cushing

SPDX-License-Identifier: CC-BY-NC-SA-4.0
-->

# java-commons
>>>>>>> after updating

SPDX-License-Identifier: CC-BY-NC-SA-4.0
-->

# Contributing

## Languages

[asdf](https://asdf-vm.com) is suggested, you can use whatever you'd like to get

- Java (version in `.tool-versions`)
- Node.js (version in `.tool-versions`)

add a way to export these to your `PATH` in your `~/.profile`

## Build Tools

<<<<<<< before updating
- [Apache Maven](https://maven.apache.org/)
- [pnpm](https://pnpm.io/installation) (via Corepack)

#### Fetching Dependencies

```sh
# Heals the toolchain: asdf versions, corepack, pnpm, Node/Python deps, git hooks
sh .config/git/hooks/heal.sh
./mvnw verify
```

With direnv, entering the repository runs `.envrc`, which calls the same
healer. If `pnpm` already works, `pnpm contribute` does the same.

If you need the individual steps, they are:

```sh
asdf install
corepack --version >/dev/null 2>&1 || npm install --global corepack
corepack enable --install-directory ~/.local/bin
pnpm install --frozen-lockfile
uv sync --frozen --group dev
git config core.hooksPath .config/git/hooks
```

## Committing

Use [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).

## AI Agent Skills

This repository uses skills from [xenoterracide/agent-skills](https://github.com/xenoterracide/agent-skills), an agentic skills plugin providing AI coding agent capabilities for Java, Gradle, GitHub, and development workflows.

# License

Apache 2.0
=======
    copier update --trust

Renovate opens an update PR whenever
[base-template](https://github.com/xenoterracide/base-template) tags a
release. Merge it like any other dependency update.
>>>>>>> after updating
