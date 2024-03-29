plugins {
  id("com.android.library")
  kotlin("android")
  id("com.vanniktech.maven.publish")
}

android {
  compileSdk = 34
  defaultConfig {
    minSdk = 16
    targetSdk = 34
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    setProperty("archivesBaseName", "chip")
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
  namespace = "com.robertlevonyan.views.chip"
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("androidx.appcompat:appcompat:1.6.1")
}
