// SPDX-License-Identifier: MIT
// © Copyright 2023-2024 Caleb Cushing. All rights reserved.

rootProject.name = "java-commons"

rootDir.resolve("module").listFiles()?.forEach { file ->
  if (file.isDirectory && file?.list { _, name -> name == "build.gradle.kts" }
      ?.isNotEmpty() == true
  ) {
    val name = file.name
    include(":$name")
    project(":$name").projectDir = file("module/$name")
  } else {
    throw Exception("Invalid module directory: $file")
  }
}

pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
  }
}

plugins {
  id("com.gradle.enterprise") version ("3.16.2")
}

gradleEnterprise {
  buildScan {
    publishOnFailureIf(providers.environmentVariable("CI").isPresent)
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
  }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)

  repositories {
    mavenCentral()
    gradlePluginPortal() // this should only be necessary in buildSrc/settings.gradle.kts
  }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
