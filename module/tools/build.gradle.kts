// © Copyright 2023-2024 Caleb Cushing
// SPDX-License-Identifier: MIT

buildscript { dependencyLocking { lockAllConfigurations() } }

plugins {
  our.javalibrary
  alias(libs.plugins.module.testing)
  `jvm-test-suite`
}

dependencies {
  compileOnly(libs.jspecify)
  testImplementation(platform(libs.spring.bom))
  testImplementation(libs.junit.api)
  testImplementation(libs.vavr)
}

tasks.compileJava {
  options.release = 11
}

testing {
  suites {
    dependencyLocking { lockAllConfigurations() }
    create<JvmTestSuite>("testBlackbox") {
      useJUnitJupiter()
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

tasks.named<Checkstyle>("checkstyleTestBlackbox") {
  configFile = rootProject.file(".config/checkstyle/test.xml")
}
