plugins {
  id("com.android.application")
  id("kotlin-android")
}

android {
  compileSdk = 32
  defaultConfig {
    applicationId = "com.robertlevonyan.views.chipsample"
    minSdk = 21
    targetSdk = 32
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
    kotlinCompilerExtensionVersion = "1.1.1"
  }
  namespace = "com.robertlevonyan.views.chipsample"
}

dependencies {
  implementation(kotlin("stdlib"))

  implementation("androidx.appcompat:appcompat:1.4.2")
  implementation("com.robertlevonyan.view:MaterialChipView:3.0.5")
  implementation("com.robertlevonyan.compose:materialchip:3.0.5")
//  implementation(project(mapOf("path" to ":chip-compose")))

  implementation("androidx.compose.compiler:compiler:1.1.1")
  implementation("androidx.compose.material:material:1.1.1")
  implementation("androidx.compose.ui:ui:1.1.1")
  implementation("androidx.compose.ui:ui-tooling:1.1.1")
  implementation("androidx.compose.foundation:foundation:1.1.1")
}
