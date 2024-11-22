// © Copyright 2024 Caleb Cushing
// SPDX-License-Identifier: MIT

import com.xenoterracide.gradle.convention.publish.GithubPublicRepositoryConfiguration


plugins {
  id("com.xenoterracide.gradle.convention.coverage")
  id("com.xenoterracide.gradle.convention.publish")
  id("com.autonomousapps.dependency-analysis")
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


dependencyAnalysis {
  issues {
    all {
      onAny {
        severity("fail")
      }
    }
  }
}
