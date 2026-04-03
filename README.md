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

- [Gradle](https://docs.gradle.org/current/userguide/command_line_interface.html)
- [Yarn 4](https://yarnpkg.com/getting-started/install) (via Corepack)

#### Fetching Dependencies

In order to get snapshots of dependencies, you must have a GitHub token in your `~/.gradle/gradle.properties` file. This file should look like:

```properties
ghUsername=<your username>
ghPassword=<your token>
```

You should generate your PAT as [Github Documents here](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#authenticating-to-github-packages).

> a personal access token (classic) with at least `read:packages` scope to install packages associated with other private repositories (which `GITHUB_TOKEN` can't access).

Then run.

```sh
# Enable Corepack, install Node dev tools, run postinstall, then verify Gradle deps
corepack enable
yarn install --immutable --inline-builds --check-resolutions
yarn run -T postinstall
./gradlew dependencies
```

If you need to run the postinstall step directly, you can recreate and use the Python lock file via pip-compile (PEP 621):

```sh
# Regenerate requirements.txt from PEP 621 dependencies in pyproject.toml
pip-compile -o requirements.txt pyproject.toml

# Then install and set up commit hooks
pip install -r requirements.txt && git config core.hooksPath .config/git/hooks
```

## Committing

Use [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).

# License

Apache 2.0
