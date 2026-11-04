<!--
SPDX-FileCopyrightText: Copyright © 2026 Caleb Cushing

SPDX-License-Identifier: CC-BY-NC-SA-4.0
-->

# java-commons

## Project Overview

`java-commons` is a multi-module Maven parent project that provides reusable Java commons libraries. It is authored by Caleb Cushing and licensed under Apache-2.0.

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

## Technology Stack

- **Java**: Temurin via `asdf`, version in `.tool-versions`
- **Build Tool**: Apache Maven (wrapper: `./mvnw`)
- **Node.js**: via `asdf`, version in `.tool-versions`; pnpm via Corepack, version in `package.json`
- **Python**: via `asdf`, version in `.tool-versions`; dependencies via `uv`

### Key Maven Plugins & Quality Gates

- **Compiler**: `maven-compiler-plugin`; release and flags in `pom.xml`
- **Static Analysis**: Error Prone + NullAway (compile-time), SpotBugs, Checkstyle
- **Coverage**: JaCoCo; thresholds in `pom.xml`
- **Testing**: JUnit 5 (Jupiter), AssertJ, Vavr (test scope)
- **Javadoc**: `doclint=all,-missing`

## Project Structure

```
.
├── pom.xml                         # Parent POM
├── module/
│   └── tools/                      # Current library module
│       ├── pom.xml
│       └── src/
│           ├── main/java/
│           │   ├── module-info.java
│           │   └── com/xenoterracide/tools/java/
│           │       ├── annotation/   # e.g. @Initializer, @ExcludeFromGeneratedCoverageReport
│           │       ├── collection/   # ArrayTools, CollectionTools
│           │       ├── function/     # ExceptionTools, PredicateTools
│           │       └── util/         # ObjectTools
│           └── test/java/
│               ├── module-info.java
│               └── com/xenoterracide/blackbox/  # Black-box tests
├── .config/
│   ├── checkstyle/
│   │   ├── main.xml                # Checkstyle rules for production code
│   │   └── test.xml                # Checkstyle rules for test code
│   ├── spotbugs/
│   │   └── exclude.xml             # SpotBugs suppression filter
│   └── git/hooks/                  # Git hooks (pre-commit, commit-msg, post-*)
├── .github/
│   ├── renovate.json5              # Renovate Bot configuration
│   └── workflows/
│       ├── build.yml               # Reusable Maven build workflow
│       └── pre-commit.yml          # License + Prettier checks
├── .mvn/
│   └── jvm.config                  # JDK compiler exports for Error Prone
├── package.json                    # Node dev-tool manifest (prettier, lint-staged, etc.)
├── pyproject.toml                  # Python project metadata (uv)
└── REUSE.toml                      # REUSE license compliance overrides
```

## Build and Test Commands

### Maven

```bash
# Compile, test, and run all quality checks (checkstyle, spotbugs, jacoco)
./mvnw verify

# Run only unit tests
./mvnw test

# Run a specific module
./mvnw -pl module/tools verify

# Skip checks during development (use sparingly)
./mvnw test -Dcheckstyle.skip=true -Dspotbugs.skip=true -Djacoco.skip=true
```

### Node / pnpm

```bash
# Install Node dependencies and set up git hooks
pnpm contribute

# Run linting (prettier + REUSE license compliance)
pnpm lint
pnpm lint:prettier
pnpm lint:reuse
```

### Python / uv

```bash
# Sync Python environment (includes `reuse` tool for license compliance)
uv sync --frozen --group dev
```

## Code Style Guidelines

### Formatting

- **Indent**: 2 spaces
- **Line endings**: LF
- **Charset**: UTF-8
- **Print width**: 120 characters (Prettier)
- **Trailing whitespace**: trimmed
- **Final newline**: required

All source files are formatted with **Prettier** (including Java via `prettier-plugin-java`).

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

## Testing Instructions

- Tests live in `src/test/java` under the **`com.xenoterracide.blackbox`** package (black-box testing style).
- The test module (`module-info.java`) opens its packages to `org.junit.platform.commons`.
- Maven Surefire runs unit tests on the **module path** (`useModulePath=true`).
- JaCoCo enforces the instruction-coverage rule in `pom.xml`.
- SpotBugs and Checkstyle run on both main and test code.

```bash
# Fast feedback loop
./mvnw -pl module/tools test

# Full verification (use before pushing)
./mvnw verify
```

## Git Workflow

### Conventional Commits

Commit messages are validated by a git hook. Allowed types (from `git-conventional-commits.yaml`):

`ci`, `feat`, `fix`, `perf`, `refactor`, `style`, `test`, `build`, `ops`, `docs`, `chore`, `merge`, `revert`

### Hooks

Hooks live in `.config/git/hooks`; `contribute` and `.envrc` set `core.hooksPath` to it:

- **pre-commit**: runs `lint-staged` (format + license headers)
- **commit-msg**: validates conventional commit format
- **post-checkout / post-merge**: reinstall dependencies when a lockfile changed

## Security Considerations

1. **CI Detection**: All git hooks exit immediately when `[ -n "$CI" ]` is true.
2. **Lockfile Integrity**: pnpm installs use `--frozen-lockfile` to prevent unexpected lockfile mutations.
3. **JVM Config**: `.mvn/jvm.config` exports required JDK compiler internals safely for Error Prone/NullAway.
4. **Dependency Updates**: Renovate is configured with automerge for minor/patch Maven updates and scheduled grouping for npm/asdf dev dependencies.

## Development Setup

1. Install prerequisites via `asdf` (reads `.tool-versions`):
   ```bash
   asdf install
   ```
2. Install Corepack once. Node stopped shipping it at 25.0.0, and every script here runs through pnpm, which Corepack provides.
   ```bash
   npm install --global corepack
   corepack enable --install-directory ~/.local/bin
   ```
3. Run the contribute script. It re-runs the Corepack setup, installs the pinned pnpm, syncs Node/Python deps, and points `core.hooksPath` at `.config/git/hooks`:
   ```bash
   pnpm contribute
   ```
4. Verify the build:
   ```bash
   ./mvnw verify
   ```
