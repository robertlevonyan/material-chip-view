buildscript {
  repositories {
    jcenter()
    google()
    mavenCentral()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:4.2.0")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
    classpath("com.vanniktech:gradle-maven-publish-plugin:0.15.0")
    classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.4.10.2")
  }
}

allprojects {
  repositories {
    google()
    jcenter()
    mavenCentral()
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}
