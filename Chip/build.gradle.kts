plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.maven.publish)
}

android {
  compileSdk = 35
  defaultConfig {
    minSdk = 16
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
  implementation(libs.androidx.appcompat)
}
