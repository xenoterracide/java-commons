// © Copyright 2023-2024 Caleb Cushing
// SPDX-License-Identifier: MIT

import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

buildscript { dependencyLocking { lockAllConfigurations() } }

plugins {
  our.javalibrary
  alias(libs.plugins.module.testing)
  `jvm-test-suite`
}

dependencies {
  compileOnly(libs.jspecify)
}

tasks.compileJava {
  options.release = 11
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

tasks.withType<Test>().configureEach {
  dependencyLocking { lockAllConfigurations() }
  useJUnitPlatform()
  reports {
    junitXml.required.set(false)
    html.required.set(false)
  }
  testLogging {
    lifecycle {
      showStandardStreams = true
      displayGranularity = 2
      exceptionFormat = TestExceptionFormat.FULL
      events.addAll(
        listOf(
          TestLogEvent.SKIPPED,
          TestLogEvent.FAILED,
        ),
      )
    }
  }
  inputs.dir(rootProject.file("buildSrc/src/main"))
}
