// Copyright 2023 - 2025 Caleb Cushing
//
// SPDX-License-Identifier: MIT

plugins {
  `java-library`
}

dependencyLocking {
  lockAllConfigurations()
}

configurations.configureEach {
  exclude(group = "org.slf4j", module = "slf4j-nop")
  exclude(group = "junit", module = "junit")

  resolutionStrategy {
    componentSelection {
      all {
        val nonRelease = Regex("^[\\d.]+-(RC|M|ea|beta|alpha).*$")
        if (candidate.group != "com.xenoterracide") {
          if (candidate.version.matches(nonRelease)) reject("no pre-release")
          if (candidate.version.endsWith("-SNAPSHOT")) reject("no snapshots")
        } else if (candidate.version.matches(nonRelease)) {
          logger.info("allowing: {}", candidate)
        }

        if (candidate.module == "nullaway") {
          val reason = "crash https://github.com/uber/NullAway/issues/533"
          if (candidate.version.matches(Regex("^0\\.9\\.[34]$"))) reject(reason)
        }
      }
    }
  }
}

configurations.matching { it.name == "runtimeClasspath" || it.name == "testRuntimeClasspath" }.configureEach {
  exclude(group = "com.google.code.findbugs", module = "jsr305")
  exclude(group = "com.google.errorprone", module = "error_prone_annotations")
  exclude(group = "org.checkerframework", module = "checker-qual")
}

dependencies {
  modules {
    module("org.springframework.boot:spring-boot-starter-logging") {
      replacedBy(
        "org.springframework.boot:spring-boot-starter-log4j2",
        "Use Log4j2 instead of Logback",
      )
    }
  }
}
