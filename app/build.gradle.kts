plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("android.extensions")
  kotlin("kapt")
}

android {
  compileSdkVersion(AppConfig.compileSdk)
  buildToolsVersion(AppConfig.buildToolsVersion)

  defaultConfig {
    applicationId = "br.com.faustfelipe.android.companiesys"
    minSdkVersion(AppConfig.minSdk)
    targetSdkVersion(AppConfig.targetSdk)
    versionCode(AppConfig.versionCode)
    versionName(AppConfig.versionName)

    testInstrumentationRunner = AppConfig.androidTestInstrumentation
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
    getByName("debug") {
      isMinifyEnabled = false
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion . VERSION_1_8
    targetCompatibility = JavaVersion . VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  implementation(AppDependencies.Kotlin.kotlinLibraries)
  implementation(AppDependencies.AndroidX.appLibraries)
  implementation(AppDependencies.Material.material)
  implementation(AppDependencies.AndroidX.Lifecycle.lifecycleLibraries)
  implementation(AppDependencies.AndroidX.Navigation.navigationLibraries)
  implementation(AppDependencies.Koin.koinLibraries)
  implementation(AppDependencies.Koin.viewModelKoinLibraries)
  testImplementation(AppDependencies.TestLibs.testLibraries)
  androidTestImplementation(AppDependencies.TestLibs.androidTestLibraries)
}