plugins {
  id("com.android.application")
  id("kotlin-android")
}

android {
  compileSdk = 34
  defaultConfig {
    applicationId = "com.robertlevonyan.views.chipsample"
    minSdk = 21
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.3"
  }
  namespace = "com.robertlevonyan.views.chipsample"
}

dependencies {
  implementation(kotlin("stdlib"))

  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.robertlevonyan.view:MaterialChipView:3.0.8")
//  implementation("com.robertlevonyan.compose:materialchip:3.0.6")
//  implementation("com.robertlevonyan.compose:material3chip:1.0.1")
//  implementation(project(mapOf("path" to ":chip-compose-m3")))

  implementation("androidx.compose.compiler:compiler:1.5.3")
  implementation("androidx.compose.material:material:1.5.4")
  implementation("androidx.compose.material3:material3:1.1.2")
  implementation("androidx.compose.ui:ui:1.5.4")
  implementation("androidx.compose.ui:ui-tooling:1.5.4")
  implementation("androidx.compose.foundation:foundation:1.5.4")
}
