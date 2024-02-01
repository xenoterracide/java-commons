// SPDX-License-Identifier: MIT
// Copyright © 2023-2024 Caleb Cushing.
buildscript { dependencyLocking.lockAllConfigurations() }

plugins { our.javalibrary }

dependencies {
  implementation(platform(libs.spring.bom))
  implementation(libs.spring.boot.autoconfigure)

  testRuntimeOnly(platform(libs.spring.bom))
  testRuntimeOnly(libs.bundles.junit.platform)
  testRuntimeOnly(libs.bundles.spring.test)

  testImplementation(platform(libs.spring.bom))
  testImplementation(libs.junit.api)
  testImplementation(libs.spring.boot.test.autoconfigure)
  modules {
    module("org.springframework.boot:spring-boot-starter-logging") {
      replacedBy(
        "org.springframework.boot:spring-boot-starter-log4j2",
        "Use Log4j2 instead of Logback",
      )
    }
  }
}

tasks.withType<JacocoCoverageVerification>().configureEach {
  dependsOn(project.tasks.withType<JacocoReport>())
  violationRules { rule { limit { minimum = 0.3.toBigDecimal() } } }
}