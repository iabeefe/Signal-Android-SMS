plugins {
<<<<<<< HEAD
  id("org.jetbrains.kotlin.jvm")
  id("java-library")
  id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("java-library")
    id("org.jlleitschuh.gradle.ktlint") version "11.1.0"
=======
    id("org.jetbrains.kotlin.jvm")
    id("java-library")
    id("org.jlleitschuh.gradle.ktlint") version "11.1.0"
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
}

val signalJavaVersion: JavaVersion by rootProject.extra
val signalKotlinJvmTarget: String by rootProject.extra

java {
  sourceCompatibility = signalJavaVersion
  targetCompatibility = signalJavaVersion
}

kotlin {
  jvmToolchain {
    languageVersion = JavaLanguageVersion.of(signalKotlinJvmTarget)
  }
}

// NOTE: For now, in order to run ktlint on this project, you have to manually run ./gradlew :build-logic:tools:ktlintFormat
//       Gotta figure out how to get it auto-included in the normal ./gradlew ktlintFormat
ktlint {
  version.set("1.2.1")
}

dependencies {
  implementation(gradleApi())

  implementation(libs.dnsjava)
  testImplementation(testLibs.junit.junit)
  testImplementation(testLibs.mockk)
}
