plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.compose)
  alias(libs.plugins.maven.publish)
}

android {
  compileSdk = 35

  defaultConfig {
    minSdk = 21

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
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
  namespace = "com.robertlevonyan.chip.compose"
}

dependencies {
  implementation(kotlin("stdlib"))

  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui.tooling)
}