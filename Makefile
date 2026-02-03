# SPDX-FileCopyrightText: Copyright © 2024-2026 Caleb Cushing
#
# SPDX-License-Identifier: MIT

GRADLE_DIR := $(wildcard ./.gradle/)
BUILD_DIRS := $(wildcard ./build/ */build/ ./module/*/build/)
CONFIGURATION_CACHE := $(wildcard $(GRADLE_DIR)configuration-cache/)

.PHONY: build
build:
	./gradlew build --console=plain

.PHONY: clean
clean:
	./gradlew clean

clean-cc: $(CONFIGURATION_CACHE)
	- rm -rf $(CONFIGURATION_CACHE)

ci-build:
	./gradlew build --build-cache --scan

ci-full:
	./gradlew build --no-build-cache --no-configuration-cache --scan

clean-build:
	- rm -rf $(BUILD_DIRS)

clean-gradle:
	- rm -rf $(GRADLE_DIR)

clean-lockfiles:
	find . -name '*gradle.lockfile' -delete

up-wrapper:
	./gradlew wrapper --write-locks && ./gradlew wrapper

up-all-deps:
	./gradlew build --write-locks --scan --console=plain | grep -e FAILED -e https
