// © Copyright 2023-2024 Caleb Cushing
// SPDX-License-Identifier: MIT

buildscript { dependencyLocking { lockAllConfigurations() } }

plugins {
  our.javalibrary
  alias(libs.plugins.module.testing)
}

dependencies {
  compileOnlyApi(libs.jspecify)
  testImplementation(platform(libs.spring.bom))
  testImplementation(libs.junit.api)
  testImplementation(libs.vavr)
}

tasks.compileJava {
  options.release = 11
}

javaModuleTesting.whitebox(testing.suites["test"]) {
  requires.add("org.junit.jupiter.api")
  requires.add("org.assertj.core")
  requires.add("io.vavr")
}
