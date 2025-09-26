// SPDX-FileCopyrightText: Copyright © 2023 - 2025 Caleb Cushing
//
// SPDX-License-Identifier: MIT

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
  `java-library`
  `java-test-fixtures`
}

val libs = the<LibrariesForLibs>()

testing {
  suites {
    withType<JvmTestSuite>().configureEach {
      dependencies {
        implementation(platform(libs.junit.bom))
        implementation.bundle(libs.bundles.test.impl)
        runtimeOnly.bundle(libs.bundles.test.runtime)
      }
    }
  }
}

val available =
  tasks.register("tests available") {
    val java: Provider<FileCollection> = sourceSets.test.map { it.java }
    doLast {
      if (java.get().isEmpty) throw RuntimeException("no tests found")
    }
  }

tasks.withType<Test>().configureEach {
  useJUnitPlatform()
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
  finalizedBy(available)
}
