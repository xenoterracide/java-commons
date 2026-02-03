#!/usr/bin/env sh
# SPDX-FileCopyrightText: Copyright © 2026 Caleb Cushing
#
# SPDX-License-Identifier: MIT

# Success if no output; matches previous Makefile behavior.
./gradlew dependencies --write-locks --console=plain | grep -e FAILED || exit 0
