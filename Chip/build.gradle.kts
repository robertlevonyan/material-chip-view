import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
  id("com.android.library")
  kotlin("android")
  id("maven-publish")
}

android {
  compileSdkVersion(30)
  defaultConfig {
    minSdkVersion(16)
    targetSdkVersion(30)
    versionCode = 1
    versionName = "1.0"
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
publishing {
  publications {
    create<MavenPublication>("debug") {
      groupId = "com.robertlevonyan.view"
      artifactId = "chip"
      version = "2.2.0"
      artifact("$buildDir/outputs/aar/chip-debug.aar")
    }
  }
  repositories {
    maven {
      name = "MaterialChipView"
      url = uri("https://maven.pkg.github.com/robertlevonyan/materialChipView")
      credentials {
        username = gradleLocalProperties(rootDir)["gpr.usr"] as String? ?: System.getenv("GPR_USER")
        password = gradleLocalProperties(rootDir)["gpr.key"] as String? ?: System.getenv("GPR_API_KEY")
      }
    }
  }
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("androidx.appcompat:appcompat:1.2.0")
  testImplementation("junit:junit:4.13.2")
}
