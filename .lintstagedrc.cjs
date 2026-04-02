// SPDX-FileCopyrightText: Copyright © 2025, 2026 Caleb Cushing
//
// SPDX-License-Identifier: MIT

const prettier = "prettier --cache --ignore-unknown --write";
const reuse = "uv run --frozen --group dev reuse annotate";
const copyright = "--copyright 'Caleb Cushing' --merge-copyrights";
const symbol = "--copyright-prefix spdx-string-symbol";

const licenseCode = "--license 'Apache-2.0'";
const licenseConfiguration = "--license 'CC0-1.0' --fallback-dot-license";
const licenseDocumentation = "--license 'CC-BY-NC-4.0'";
const licenseScripts = "--license 'MIT' --fallback-dot-license";

const withFiles = (command, files) => `${command} ${files.map((file) => `"${file.replace(/"/g, '\\"')}"`).join(" ")}`;

const run = (commands) => (files) => {
  const cmds = Array.isArray(commands) ? commands : [commands];
  return cmds.map((command) => withFiles(command, files));
};

module.exports = {
  "!(package).json": run([`${reuse} ${copyright} ${symbol} ${licenseConfiguration}`, prettier]),
  "package.json": run([`${reuse} ${copyright} ${symbol} ${licenseScripts}`, prettier]),
  "{.config/git/hooks/**,**/*.*sh}": run([
    `${reuse} ${copyright} ${symbol} ${licenseScripts} --style python`,
    prettier,
  ]),
  "*.{md,adoc}": run([`${reuse} ${copyright} ${symbol} ${licenseDocumentation}`, prettier]),
  "*.{xml,yaml,yml,properties,toml,json5}": run([`${reuse} ${copyright} ${licenseConfiguration} ${symbol}`, prettier]),
  ".github/**/*.yml": run([`${reuse} ${copyright} ${symbol} ${licenseScripts}`, prettier]),
  "*.{js,cjs}": run([`${reuse} ${copyright} ${symbol} ${licenseScripts}`, prettier]),
  ".{*ignore,editorconfig,gitattributes,mailmap}": run([
    `${reuse} ${copyright} ${symbol} ${licenseConfiguration}`,
    prettier,
  ]),
  "*.properties": run([`${reuse} ${copyright} ${licenseConfiguration}`, prettier]),
  "*.{ts,java}": run([`${reuse} ${copyright} ${symbol} ${licenseCode}`, prettier]),
};
