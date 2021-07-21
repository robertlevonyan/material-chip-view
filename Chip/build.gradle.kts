plugins {
  id("com.android.library")
  kotlin("android")
  id("com.vanniktech.maven.publish")
}

android {
  compileSdkVersion(31)
  defaultConfig {
    minSdkVersion(16)
    targetSdkVersion(31)
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    setProperty("archivesBaseName", "chip")
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

allprojects {
  plugins.withId("com.vanniktech.maven.publish") {
    mavenPublish {
      sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
    }
  }
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("androidx.appcompat:appcompat:1.3.0")
  testImplementation("junit:junit:4.13.2")
}
