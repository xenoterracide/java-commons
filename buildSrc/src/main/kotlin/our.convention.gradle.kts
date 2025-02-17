// SPDX-FileCopyrightText: Copyright © 2024 - 2025 Caleb Cushing
//
// SPDX-License-Identifier: MIT

import com.xenoterracide.gradle.convention.publish.GithubPublicRepositoryConfiguration
import org.gradle.accessors.dm.LibrariesForLibs


plugins {
  id("com.xenoterracide.gradle.convention.checkstyle")
  id("com.xenoterracide.gradle.convention.coverage")
  id("com.xenoterracide.gradle.convention.publish")
  id("com.xenoterracide.gradle.convention.spotbugs")
}

val libs = the<LibrariesForLibs>()
dependencies {
  spotbugs(libs.spotbugs)
}

repositoryHost(GithubPublicRepositoryConfiguration())
repositoryHost.namespace.set("xenoterracide")

publicationLegal {
  inceptionYear.set(2024)
  spdxLicenseIdentifiers.add("Apache-2.0")
}

publishing {
  publications {
    register<MavenPublication>(project.name) {
      from(components["java"])
    }
  }
}
