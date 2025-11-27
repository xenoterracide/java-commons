// Copyright 2023 - 2025 Caleb Cushing
//
// SPDX-License-Identifier: MIT

buildscript { dependencyLocking { lockAllConfigurations() } }

plugins {
  our.javalibrary
  alias(libs.plugins.module.testing)
  `jvm-test-suite`
}

dependencies {
  compileOnly(libs.jspecify)
  spotbugs(libs.jspecify)
}

tasks.compileJava {
  options.release = 17
}

testing {
  suites {
    val test by getting(JvmTestSuite::class) {
      dependencies {
        implementation(platform(libs.spring.bom))
        implementation(libs.junit.api)
        implementation(libs.assertj)
        implementation(libs.vavr)
        implementation(project(path))

        runtimeOnly(platform(libs.spring.bom))
        runtimeOnly(libs.junit.engine)
        runtimeOnly(libs.junit.launcher)
      }
    }
  }
}
