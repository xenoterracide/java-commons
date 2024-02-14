// SPDX-License-Identifier: MIT
// Copyright © 2024 Caleb Cushing.
plugins {
  `maven-publish`
}

val username = "xenoterracide"
val githubUrl = "https://github.com"
val repoShort = "$username/java-commons"

publishing {
  publications {
    create<MavenPublication>("maven") {
      versionMapping {
        allVariants {
          fromResolutionResult()
        }
      }
      pom {
        artifactId = project.name
        groupId = project.group.toString()
        version = project.version.toString()
        description = project.description
        inceptionYear = "2024"
        url = "$githubUrl/$repoShort"
        licenses {
          license {
            name = "Apache-2.0"
            url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            distribution = "repo"
          }
        }
        developers {
          developer {
            id = username
            name = "Caleb Cushing"
            email = "xenoterracide@gmail.com"
          }
        }
        scm {
          connection = "$githubUrl/$repoShort.git"
          developerConnection = "scm:git:$githubUrl/$repoShort.git"
          url = "$githubUrl/$repoShort"
        }
      }
      from(components["java"])
    }
  }

  repositories {
    maven {
      name = "GitHubPackages"
      url = uri("https://maven.pkg.github.com/$repoShort")
      credentials(PasswordCredentials::class)
    }
  }
}
