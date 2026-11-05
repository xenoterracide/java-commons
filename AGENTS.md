<!--
SPDX-FileCopyrightText: Copyright © 2026 Caleb Cushing

SPDX-License-Identifier: CC-BY-NC-SA-4.0
-->

# java-commons

## Project Overview

`java-commons` is a multi-module Maven parent project of reusable Java libraries by Caleb Cushing. License: Apache-2.0; per-file licenses in `REUSE.toml`.

- **Group ID**: `com.xenoterracide`
- **Artifact ID**: `java-commons-parent`

## Source of Truth

Read these files instead of trusting a copy of their contents here. Versions and rules drift; these do not.

| For                                            | See                                        |
| ---------------------------------------------- | ------------------------------------------ |
| Install, update, usage, contribution           | `README.md`                                |
| Tool versions (Java, Node, ktlint, Python)     | `.tool-versions`                           |
| Maven version                                  | `.mvn/wrapper/maven-wrapper.properties`    |
| pnpm version                                   | `packageManager` in `package.json`         |
| Java release, plugins, quality-gate thresholds | `pom.xml`                                  |
| Node dev dependencies                          | `package.json`, `pnpm-lock.yaml`           |
| Python dependencies                            | `pyproject.toml`, `uv.lock`                |
| Formatting rules                               | `.editorconfig`, `.prettierrc.cjs`         |
| Conventional commit types                      | `git-conventional-commits.yaml`            |
| Pre-commit rules (license + format)            | `.lintstagedrc.cjs`                        |
| License headers                                | `REUSE.toml`, `.lintstagedrc.cjs`          |
| Git hooks                                      | `.config/git/hooks`                        |
| Static-analysis config                         | `.config/checkstyle/`, `.config/spotbugs/` |
| CI workflows                                   | `.github/workflows/`                       |
| Renovate config                                | `.github/renovate.json5`                   |
| Design decisions                               | `docs/superpowers/specs/`                  |

## Build and Test Commands

### Maven

```bash
# Compile, test, and run all quality checks
./mvnw verify

# Run only unit tests
./mvnw test

# Run one module
./mvnw -pl module/tools verify

# Skip checks while iterating
./mvnw test -Dcheckstyle.skip=true -Dspotbugs.skip=true -Djacoco.skip=true
```

### Node / pnpm

```bash
# Heal the toolchain (asdf, corepack, Node/Python deps, git hooks)
pnpm contribute

# Lint (prettier + REUSE license compliance)
pnpm lint
pnpm lint:prettier
pnpm lint:reuse
```

### Python / uv

```bash
# Sync the Python environment; brings `reuse`
uv sync --frozen --group dev
```

## Quality Gates

- Error Prone + NullAway at compile time; `.mvn/jvm.config` exports the JDK internals they need
- Checkstyle on main and test code; SpotBugs on main
- JaCoCo instruction coverage
- Javadoc doclint `all,-missing`
- Surefire runs tests on the module path

## Testing Instructions

- Tests live in `src/test/java` under **`com.xenoterracide.blackbox`** (black-box style).
- The test `module-info.java` opens its packages to `org.junit.platform.commons`.
- Test stack: JUnit Jupiter, AssertJ, Vavr (test scope).

## Code Style Guidelines

### Formatting

Rules live in `.editorconfig` and `.prettierrc.cjs`. Prettier formats every file, Java included (`prettier-plugin-java`).

### Java Conventions

- Prefer `var` over explicit types where readable.
- Prefer **immutability**: `final` fields, `record` classes, `List.of()`, unmodifiable collections.
- Prefer **package-private** visibility over `private` for methods and classes; fields should remain `private`.
- Use **non-null by default**:
  - Modules and packages are annotated with `@NullMarked`.
  - Use `@Nullable` only when a value can genuinely be null.
- Utility classes must have a `private` no-arg constructor and be `final`.
- Every package must contain a `package-info.java` with appropriate Javadoc and `@NullMarked`.
- Imports: static imports first, then third-party, sorted alphabetically; no star imports.

### Licensing / SPDX

- **Every file** must have an SPDX header.

<!-- REUSE-IgnoreStart -->

- Java source files use `SPDX-License-Identifier: Apache-2.0`.
- Configuration/XML/TOML/YAML/JSON5 files use `CC0-1.0`.
- Shell scripts and JS/CJS files use `MIT`.
- Markdown/ADoc use `CC-BY-NC-SA-4.0`.

<!-- REUSE-IgnoreEnd -->

- `lint-staged` automatically adds headers via `reuse annotate` on commit.

## Git Workflow

### Conventional Commits

Commit messages are validated by a git hook. Allowed types (from `git-conventional-commits.yaml`):

`ci`, `feat`, `fix`, `perf`, `refactor`, `style`, `test`, `build`, `ops`, `docs`, `chore`, `merge`, `revert`

### Hooks

Hooks live in `.config/git/hooks`.

- **pre-commit**: runs `lint-staged` (format + license headers)
- **commit-msg**: validates conventional commit format
- **post-checkout / post-merge**: run `heal.sh` when `.tool-versions` or a
  lockfile changed

Hooks exit immediately in CI (`[ -n "$CI" ]`).

### Renovate

Renovate opens dependency PRs, automerges minor/patch Maven updates, and groups npm/asdf updates on a schedule. Expect develop to move under you.
