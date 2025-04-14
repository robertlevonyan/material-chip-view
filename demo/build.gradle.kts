plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose)
}

android {
    compileSdk = 35
    defaultConfig {
        applicationId = "com.robertlevonyan.views.chipsample"
        minSdk = 21
        targetSdk = 35
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
    namespace = "com.robertlevonyan.views.chipsample"
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(libs.androidx.appcompat)
//  implementation("com.robertlevonyan.view:MaterialChipView:3.0.8")
//  implementation("com.robertlevonyan.compose:materialchip:3.0.6")
//  implementation("com.robertlevonyan.compose:material3chip:1.0.1")
    implementation(project(mapOf("path" to ":chip-compose-m3")))
    implementation(libs.material)

    implementation(libs.androidx.compose.material2)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.foundation)
}
