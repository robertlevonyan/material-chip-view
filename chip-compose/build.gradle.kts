plugins {
  id("com.android.library")
  kotlin("android")
  id("com.vanniktech.maven.publish")
}

android {
  compileSdk = 33

  defaultConfig {
    minSdk = 21
    targetSdk = 33

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
    freeCompilerArgs.toMutableList().addAll(
      listOf(
        "-Xallow-jvm-ir-dependencies",
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
      )
    )
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.4.7"
  }
  namespace = "com.robertlevonyan.chip.compose"
}

dependencies {
  implementation(kotlin("stdlib"))

  implementation("androidx.compose.compiler:compiler:1.4.7")
  implementation("androidx.compose.ui:ui:1.4.3")
  implementation("androidx.compose.material:material:1.4.3")
  implementation("androidx.compose.ui:ui-tooling:1.4.3")
}