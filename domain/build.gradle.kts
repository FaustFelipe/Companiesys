plugins {
  id("java-library")
  id("kotlin")
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_7
  targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
  implementation(AppDependencies.Kotlin.kotlinLibraries)
  implementation(AppDependencies.Koin.koinLibraries)
}