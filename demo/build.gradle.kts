plugins {
  id("com.android.application")
  id("kotlin-android")
}

android {
  compileSdk = 33
  defaultConfig {
    applicationId = "com.robertlevonyan.views.chipsample"
    minSdk = 21
    targetSdk = 33
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
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.4.2"
  }
  namespace = "com.robertlevonyan.views.chipsample"
}

dependencies {
  implementation(kotlin("stdlib"))

  implementation("androidx.appcompat:appcompat:1.6.1")
//  implementation("com.robertlevonyan.view:MaterialChipView:3.0.5")
//  implementation("com.robertlevonyan.compose:materialchip:3.0.6")
  implementation("com.robertlevonyan.compose:material3chip:1.0.0-alpha03")
//  implementation(project(mapOf("path" to ":chip-compose-m3")))

  implementation("androidx.compose.compiler:compiler:1.4.2")
  implementation("androidx.compose.material:material:1.3.1")
  implementation("androidx.compose.material3:material3:1.1.0-alpha06")
  implementation("androidx.compose.ui:ui:1.3.3")
  implementation("androidx.compose.ui:ui-tooling:1.3.3")
  implementation("androidx.compose.foundation:foundation:1.3.1")
}
