// © Copyright 2023-2024 Caleb Cushing
// SPDX-License-Identifier: MIT

buildscript { dependencyLocking.lockAllConfigurations() }

plugins { our.javalibrary }

dependencies {
  compileOnlyApi(libs.jspecify)
  testImplementation(platform(libs.spring.bom))
  testImplementation(libs.junit.api)
  testImplementation(libs.vavr)
}

tasks.compileJava {
  options.release = 11
}
