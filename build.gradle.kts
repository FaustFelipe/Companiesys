// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  repositories {
    google()
    mavenCentral()
    jcenter()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:${Versions.AppLevel.gradle}")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.AppLevel.kotlin}")
  }
}

allprojects {
  repositories {
    google()
    mavenCentral()
    jcenter() // Warning: this repository is going to shut down soon
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}