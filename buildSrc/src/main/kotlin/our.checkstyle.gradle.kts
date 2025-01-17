// © Copyright 2023-2024 Caleb Cushing
// SPDX-License-Identifier: MIT

plugins {
  `java-test-fixtures`
  checkstyle
}

tasks.withType<Checkstyle>().configureEach {
  isShowViolations = true
}

fun checkstyleConfig(filename: String): File {
  val path = ".config/checkstyle/$filename"
  val f = file(path)
  return if (f.exists()) f else rootProject.file(path)
}

tasks.named<Checkstyle>("checkstyleMain") {
  configFile = checkstyleConfig("main.xml")
}

tasks.named<Checkstyle>("checkstyleTestFixtures") {
  configFile = checkstyleConfig("main.xml")
}

tasks.named<Checkstyle>("checkstyleTest") {
  configFile = checkstyleConfig("test.xml")
}
