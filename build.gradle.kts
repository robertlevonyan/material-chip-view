plugins {
  id("com.android.application") version "8.0.1" apply false
  id("com.android.library") version "8.0.1" apply false
  id("org.jetbrains.dokka") version "1.7.20" apply false
  id("com.vanniktech.maven.publish") version "0.24.0" apply false
  kotlin("android") version "1.8.21" apply false
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}
