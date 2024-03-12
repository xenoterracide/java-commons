// © Copyright 2023-2024 Caleb Cushing
// SPDX-License-Identifier: MIT

buildscript { dependencyLocking { lockAllConfigurations() } }

plugins {
  our.javalibrary
  alias(libs.plugins.module.testing)
  `jvm-test-suite`
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

testing {
  suites {
    dependencyLocking { lockAllConfigurations() }
    create<JvmTestSuite>("testBlackbox") {
      useJUnitJupiter()
      dependencies {
        implementation(platform(libs.spring.bom))
        implementation(libs.junit.api)
        implementation(libs.assertj)
        implementation(project(path))

        runtimeOnly(platform(libs.spring.bom))
        runtimeOnly(libs.junit.engine)
        runtimeOnly(libs.junit.launcher)
      }
    }
  }
}

javaModuleTesting.whitebox(testing.suites["test"]) {
  requires.add("org.junit.jupiter.api")
  requires.add("org.assertj.core")
  requires.add("io.vavr")
}

tasks.named<Checkstyle>("checkstyleTestBlackbox") {
  configFile = rootProject.file(".config/checkstyle/test.xml")
}
