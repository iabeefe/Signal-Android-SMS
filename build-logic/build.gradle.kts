<<<<<<< HEAD
import org.gradle.kotlin.dsl.extra

buildscript {
    val kotlinVersion by extra("1.9.20")

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

apply(from = "${rootDir}/../constants.gradle.kts")

||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
=======
buildscript {
    val kotlinVersion by extra("1.7.20")

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
