plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.compose) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.dokka) apply false
  alias(libs.plugins.maven.publish) apply false
}

tasks.register("clean", Delete::class) {
  delete(layout.buildDirectory)
}
