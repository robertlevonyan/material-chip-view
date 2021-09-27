plugins {
  id("com.android.application")
  id("kotlin-android")
}

android {
  compileSdk = 31
  defaultConfig {
    applicationId = "com.robertlevonyan.views.chipsample"
    minSdk = 21
    targetSdk = 31
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.0.2"
  }
}

dependencies {
  implementation(kotlin("stdlib"))

  implementation("androidx.appcompat:appcompat:1.3.1")
  implementation("com.robertlevonyan.view:MaterialChipView:2.2.7")
//  implementation("com.robertlevonyan.compose:materialchip:3.0.0-beta03")
  implementation(project(mapOf("path" to ":chip-compose")))

  implementation("androidx.compose.compiler:compiler:1.0.2")
  implementation("androidx.compose.material:material:1.0.2")
  implementation("androidx.compose.ui:ui:1.0.2")
  implementation("androidx.compose.ui:ui-tooling:1.0.2")
  implementation("androidx.compose.foundation:foundation:1.0.2")
}
