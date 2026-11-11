<!--
SPDX-FileCopyrightText: Copyright © 2015-2026 Caleb Cushing

SPDX-License-Identifier: CC-BY-NC-SA-4.0
-->

# java-commons

## Languages

<<<<<<< before updating
[asdf](https://asdf-vm.com) is suggested, you can use whatever you'd like to get
=======
- `npm run contribute` installs tools and dependencies, and heals the
  toolchain through `.config/git/hooks/heal.sh`. Node 25+ drops corepack,
  so the bootstrap starts from npm.
- `direnv allow` (if you use direnv) loads the environment.
>>>>>>> after updating

- Java (version in `.tool-versions`)
- Node.js (version in `.tool-versions`)

add a way to export these to your `PATH` in your `~/.profile`

## Build Tools

- [Apache Maven](https://maven.apache.org/)
- [pnpm](https://pnpm.io/installation) (via Corepack)

### Fetching Dependencies

<<<<<<< before updating
```sh
# Heals the toolchain: asdf versions, corepack, pnpm, Node/Python deps, git hooks
sh .config/git/hooks/heal.sh
./mvnw verify
```
=======
    uv run --group dev copier update --trust
>>>>>>> after updating

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

## License

- **Code**: [Apache-2.0](LICENSES/Apache-2.0.txt)
- **Documentation**: [CC-BY-NC-SA-4.0](LICENSES/CC-BY-NC-SA-4.0.txt)
- **Configuration files**: [CC0-1.0](LICENSES/CC0-1.0.txt)
- **Scripts and manifests** (`*.sh`, `*.js`, `*.yml`, `package.json`): [MIT](LICENSES/MIT.txt)
