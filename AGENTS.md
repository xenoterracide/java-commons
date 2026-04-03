<!--
SPDX-FileCopyrightText: Copyright © 2026 Caleb Cushing

SPDX-License-Identifier: CC-BY-NC-4.0
SPDX-License-Identifier: CC-BY-NC-SA-4.0
-->

# java-commons

## Project Overview

`java-commons` is a multi-module Maven parent project that provides reusable Java commons libraries. It is authored by Caleb Cushing and licensed under Apache-2.0.

- **Group ID**: `com.xenoterracide`
- **Artifact ID**: `java-commons-parent`
- **Current Version**: `0.0.0`
- **Java Release**: 21

The repository also embeds a **shared tooling subtree** under `.share/` that supplies git hooks, AI-assisted merge workflows, and cross-project linting configuration.

## Technology Stack

- **Java**: 21+ (Temurin recommended, managed via `asdf`)
- **Build Tool**: Apache Maven (wrapper: `./mvnw`)
- **Node.js**: 24.14.1 (managed via `yarn` 4 with Plug'n'Play)
- **Python**: 3.14.3 (managed via `uv`)

### Key Maven Plugins & Quality Gates

- **Compiler**: `maven-compiler-plugin` with `release=21`
- **Static Analysis**: Error Prone + NullAway (compile-time), SpotBugs, Checkstyle
- **Coverage**: JaCoCo with a **minimum 90% instruction coverage** rule
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
│   └── git/hooks/                  # commit-msg and pre-commit hooks
├── .github/
│   ├── renovate.json5              # Renovate Bot configuration
│   └── workflows/
│       ├── build.yml               # Reusable Maven build workflow
│       └── pre-commit.yml          # License + Prettier checks
├── .share/                         # Shared tooling subtree (git hooks, merge scripts)
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

### Node / Yarn

```bash
# Install Node dependencies and set up git hooks
yarn contribute

# Run all workspace tests (includes shared tooling type-checks)
yarn test

# Run linting (prettier + REUSE license compliance)
yarn lint
yarn lint:prettier
yarn lint:reuse
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
- Java source files use `SPDX-License-Identifier: Apache-2.0`.
- Configuration/XML/TOML/YAML/JSON5 files use `CC0-1.0`.
- Shell scripts and JS/CJS files use `MIT`.
- Markdown/ADoc use `CC-BY-NC-SA-4.0`.
- `lint-staged` automatically adds headers via `reuse annotate` on commit.

## Testing Instructions

- Tests live in `src/test/java` under the **`com.xenoterracide.blackbox`** package (black-box testing style).
- The test module (`module-info.java`) opens its packages to `org.junit.platform.commons`.
- Maven Surefire runs unit tests on the **module path** (`useModulePath=true`).
- JaCoCo enforces **≥ 90% instruction coverage**.
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

Hooks are located in `.config/git/hooks` and referenced via `core.hooksPath .share/git/hooks`:

- **pre-commit**: runs `lint-staged` (format + license headers)
- **commit-msg**: validates conventional commit format

### AI-Assisted Merge

The `.share/` subtree provides merge scripts for different AI agents:

```bash
yarn merge:kimi      # Kimi CLI merge workflow
yarn merge:junie     # Junie CLI merge workflow
yarn merge:copilot   # GitHub Copilot merge workflow
```

## Security Considerations

1. **CI Detection**: All git hooks exit immediately when `[ -n "$CI" ]` is true.
2. **Lockfile Integrity**: Yarn installs use `--immutable` to prevent unexpected lockfile mutations.
3. **JVM Config**: `.mvn/jvm.config` exports required JDK compiler internals safely for Error Prone/NullAway.
4. **Dependency Updates**: Renovate is configured with automerge for minor/patch Maven updates and scheduled grouping for npm/asdf dev dependencies.

## Development Setup

1. Install prerequisites via `asdf` (reads `.tool-versions`):
   ```bash
   asdf install
   ```
2. Run the contribute script to install Node/Python deps and configure hooks:
   ```bash
   yarn contribute
   ```
3. Verify the build:
   ```bash
   ./mvnw verify
   ```
