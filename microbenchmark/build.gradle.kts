<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
@file:Suppress("UnstableApiUsage")

plugins {
  id("com.android.library")
  id("androidx.benchmark")
  id("org.jetbrains.kotlin.android")
  id("ktlint")
}

val signalBuildToolsVersion: String by rootProject.extra
val signalCompileSdkVersion: String by rootProject.extra
val signalTargetSdkVersion: Int by rootProject.extra
val signalMinSdkVersion: Int by rootProject.extra
val signalJavaVersion: JavaVersion by rootProject.extra
val signalKotlinJvmTarget: String by rootProject.extra

android {
  namespace = "org.signal.microbenchmark"
  compileSdkVersion = signalCompileSdkVersion

  compileOptions {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = signalJavaVersion
    targetCompatibility = signalJavaVersion
  }

  kotlinOptions {
    jvmTarget = signalKotlinJvmTarget
  }

  defaultConfig {
    minSdk = signalMinSdkVersion
    targetSdk = signalTargetSdkVersion

    testInstrumentationRunner = "androidx.benchmark.junit4.AndroidBenchmarkRunner"
  }

  testBuildType = "release"
  buildTypes {
    debug {
      // Since isDebuggable can't be modified by gradle for library modules,
      // it must be done in a manifest - see src/androidTest/AndroidManifest.xml
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "benchmark-proguard-rules.pro")
    }
    release {
      isDefault = true
    }
  }
}

dependencies {
  coreLibraryDesugaring(libs.android.tools.desugar)
  lintChecks(project(":lintchecks"))

  implementation(project(":core-util"))

  // Base dependencies
  androidTestImplementation(testLibs.junit.junit)
  androidTestImplementation(benchmarkLibs.androidx.test.ext.junit)
  androidTestImplementation(benchmarkLibs.androidx.benchmark.micro)

  // Dependencies of modules being tested
  androidTestImplementation(project(":libsignal-service"))
  androidTestImplementation(libs.libsignal.android)
}
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("androidx.benchmark")
    id("org.jetbrains.kotlin.android")
    id("android-constants")
    id("ktlint")
}

val signalBuildToolsVersion: String by extra
val signalCompileSdkVersion: String by extra
val signalTargetSdkVersion: Int by extra
val signalMinSdkVersion: Int by extra
val signalJavaVersion: JavaVersion by extra

android {
    namespace = "org.signal.microbenchmark"
    compileSdkVersion = signalCompileSdkVersion

    compileOptions {
        sourceCompatibility = signalJavaVersion
        targetCompatibility = signalJavaVersion
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    defaultConfig {
        minSdk = signalMinSdkVersion
        targetSdk = signalTargetSdkVersion

        testInstrumentationRunner = "androidx.benchmark.junit4.AndroidBenchmarkRunner"
    }

    testBuildType = "release"
    buildTypes {
        debug {
            // Since isDebuggable can't be modified by gradle for library modules,
            // it must be done in a manifest - see src/androidTest/AndroidManifest.xml
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "benchmark-proguard-rules.pro")
        }
        release {
            isDefault = true
        }
    }
}

dependencies {
    lintChecks(project(":lintchecks"))

    // Base dependencies
    androidTestImplementation(testLibs.junit.junit)
    androidTestImplementation(benchmarkLibs.androidx.test.ext.junit)
    androidTestImplementation(benchmarkLibs.androidx.benchmark.micro)

    // Dependencies of modules being tested
    androidTestImplementation(project(":libsignal-service"))
    androidTestImplementation(libs.libsignal.android)
    androidTestImplementation(libs.google.protobuf.javalite)
}
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("androidx.benchmark")
    id("org.jetbrains.kotlin.android")
    id("android-constants")
    id("ktlint")
}

val signalBuildToolsVersion: String by extra
val signalCompileSdkVersion: String by extra
val signalTargetSdkVersion: Int by extra
val signalMinSdkVersion: Int by extra
val signalJavaVersion: JavaVersion by extra

android {
    namespace = "org.signal.microbenchmark"
    compileSdkVersion = signalCompileSdkVersion

    compileOptions {
        sourceCompatibility = signalJavaVersion
        targetCompatibility = signalJavaVersion
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    defaultConfig {
        minSdk = signalMinSdkVersion
        targetSdk = signalTargetSdkVersion

        testInstrumentationRunner = "androidx.benchmark.junit4.AndroidBenchmarkRunner"
    }

    testBuildType = "release"
    buildTypes {
        debug {
            // Since isDebuggable can't be modified by gradle for library modules,
            // it must be done in a manifest - see src/androidTest/AndroidManifest.xml
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "benchmark-proguard-rules.pro")
        }
        release {
            isDefault = true
        }
    }
}

dependencies {
    lintChecks(project(":lintchecks"))

    // Base dependencies
    androidTestImplementation(testLibs.junit.junit)
    androidTestImplementation(benchmarkLibs.androidx.test.ext.junit)
    androidTestImplementation(benchmarkLibs.androidx.benchmark.micro)

    // Dependencies of modules being tested
    androidTestImplementation(project(":libsignal-service"))
    androidTestImplementation(libs.libsignal.android)
    androidTestImplementation(libs.google.protobuf.javalite)
}
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("androidx.benchmark")
    id("org.jetbrains.kotlin.android")
    id("android-constants")
    id("ktlint")
}

val signalBuildToolsVersion: String by extra
val signalCompileSdkVersion: String by extra
val signalTargetSdkVersion: Int by extra
val signalMinSdkVersion: Int by extra
val signalJavaVersion: JavaVersion by extra

android {
    namespace = "org.signal.microbenchmark"
    compileSdkVersion = signalCompileSdkVersion

    compileOptions {
        sourceCompatibility = signalJavaVersion
        targetCompatibility = signalJavaVersion
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    defaultConfig {
        minSdk = signalMinSdkVersion
        targetSdk = signalTargetSdkVersion

        testInstrumentationRunner = "androidx.benchmark.junit4.AndroidBenchmarkRunner"
    }

    testBuildType = "release"
    buildTypes {
        debug {
            // Since isDebuggable can't be modified by gradle for library modules,
            // it must be done in a manifest - see src/androidTest/AndroidManifest.xml
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "benchmark-proguard-rules.pro")
        }
        release {
            isDefault = true
        }
    }
}

dependencies {
    lintChecks(project(":lintchecks"))

    // Base dependencies
    androidTestImplementation(testLibs.junit.junit)
    androidTestImplementation(benchmarkLibs.androidx.test.ext.junit)
    androidTestImplementation(benchmarkLibs.androidx.benchmark.micro)

    // Dependencies of modules being tested
    androidTestImplementation(project(":libsignal-service"))
    androidTestImplementation(libs.libsignal.android)
    androidTestImplementation(libs.google.protobuf.javalite)
}
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
