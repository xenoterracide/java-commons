// © Copyright 2023-2024 Caleb Cushing
// SPDX-License-Identifier: MIT

import org.semver4j.Semver

buildscript { dependencyLocking { lockAllConfigurations() } }

plugins {
  our.spotless
  alias(libs.plugins.dependency.analysis)
  alias(libs.plugins.semver)
}

dependencyLocking { lockAllConfigurations() }

group = "com.xenoterracide"

version = providers.environmentVariable("IS_PUBLISHING")
  .map { semver.gitDescribed }
  .orElse(Semver("0.0.0")).get()

tasks.dependencies {
  dependsOn(subprojects.map { it.tasks.dependencies })
}

tasks.check {
  dependsOn(tasks.buildHealth)
}
