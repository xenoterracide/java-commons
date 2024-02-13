// SPDX-License-Identifier: MIT
// Copyright © 2024 Caleb Cushing.
import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude

// SPDX-License-Identifier: MIT
// Copyright © 2023-2024 Caleb Cushing.
buildscript {
  dependencyLocking.lockAllConfigurations()
}
plugins {
  our.spotless
  alias(libs.plugins.dependency.analysis)
}

dependencyLocking.lockAllConfigurations()

group = "com.xenoterracide"
version = "0.1.0-SNAPSHOT"

tasks.dependencies {
  dependsOn(subprojects.map { "${it.path}:dependencies" })
}

dependencyAnalysis {
  issues {
    all {
      onUnusedDependencies {
        exclude(libs.junit.parameters)
      }
    }
  }
}
