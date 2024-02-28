// SPDX-License-Identifier: MIT
// © Copyright 2023-2024 Caleb Cushing. All rights reserved.

buildscript { dependencyLocking.lockAllConfigurations() }

plugins { our.javalibrary }

version = providers.environmentVariable("VERSION").orElse("0.1.0-SNAPSHOT")

dependencies {
  testImplementation(platform(libs.spring.bom))
  testImplementation(libs.junit.api)
}
