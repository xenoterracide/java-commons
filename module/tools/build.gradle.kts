// SPDX-License-Identifier: MIT
// Copyright © 2023-2024 Caleb Cushing.
buildscript { dependencyLocking.lockAllConfigurations() }

plugins { our.javalibrary }

dependencies {
  testImplementation(platform(libs.spring.bom))
  testImplementation(libs.junit.api)
}
