<!--
SPDX-FileCopyrightText: Copyright © 2024-2026 Caleb Cushing

SPDX-License-Identifier: CC-BY-NC-4.0
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

In order to get snapshots of dependencies, you must have a GitHub token configured. This can be set via environment variables:

```sh
export GH_USERNAME=<your username>
export GH_TOKEN=<your token>
```

You should generate your PAT as [GitHub Documents here](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#authenticating-to-github-packages).

> a personal access token (classic) with at least `read:packages` scope to install packages associated with other private repositories (which `GITHUB_TOKEN` can't access).

Then run.

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

# License

Apache 2.0
