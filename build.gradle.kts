// © Copyright 2023-2024 Caleb Cushing
// SPDX-License-Identifier: MIT

buildscript { dependencyLocking { lockAllConfigurations() } }

plugins {
  our.spotless
  alias(libs.plugins.dependency.analysis)
}

dependencyLocking { lockAllConfigurations() }

group = "com.xenoterracide"

tasks.dependencies {
  dependsOn(subprojects.map { "${it.path}:dependencies" })
}

dependencyAnalysis {
  issues {
    all {
      onAny {
        severity("fail")
      }
      onUnusedDependencies {
        exclude(libs.junit.parameters)
      }
    }
  }
}
