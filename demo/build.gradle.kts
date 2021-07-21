plugins {
  id("com.android.application")
}

android {
  compileSdkVersion(31)
  defaultConfig {
    applicationId = "com.robertlevonyan.views.chipsample"
    minSdkVersion(16)
    targetSdkVersion(31)
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
}

dependencies {
  implementation("androidx.appcompat:appcompat:1.3.0")
  implementation("com.robertlevonyan.view:MaterialChipView:2.2.0")
}
