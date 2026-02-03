#!/usr/bin/env sh

# SPDX-FileCopyrightText: Copyright © 2026 Caleb Cushing
#
# SPDX-License-Identifier: MIT

set -eu

SCRIPT_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
KTLINT_VERSION=${KTLINT_VERSION}
KTLINT_BIN=${KTLINT_BIN:-"$SCRIPT_DIR/.tools/ktlint-$KTLINT_VERSION"}
KTLINT_URL=${KTLINT_URL:-"https://github.com/pinterest/ktlint/releases/download/$KTLINT_VERSION/ktlint"}

if [ ! -x "$KTLINT_BIN" ]; then
  mkdir -p "$(dirname -- "$KTLINT_BIN")"
  echo "Downloading ktlint $KTLINT_VERSION..." >&2
  wget -q -O "$KTLINT_BIN" "$KTLINT_URL"
  chmod +x "$KTLINT_BIN"
fi

exec "$KTLINT_BIN" "$@"
