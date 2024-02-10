// SPDX-License-Identifier: MIT
// Copyright © 2023-2024 Caleb Cushing.
buildscript { dependencyLocking.lockAllConfigurations() }

plugins { our.javalibrary }

dependencies {
  implementation(platform(libs.spring.bom))
  testImplementation(libs.junit.api)
}

tasks.withType<JacocoCoverageVerification>().configureEach {
  dependsOn(project.tasks.withType<JacocoReport>())
  violationRules { rule { limit { minimum = 0.3.toBigDecimal() } } }
}
