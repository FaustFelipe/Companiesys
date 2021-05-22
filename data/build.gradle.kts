plugins {
  id("com.android.library")
  kotlin("android")
}

android {
  compileSdkVersion(AppConfig.compileSdk)
  buildToolsVersion(AppConfig.buildToolsVersion)

  defaultConfig {
    minSdkVersion(AppConfig.minSdk)
    targetSdkVersion(AppConfig.targetSdk)
    versionCode(AppConfig.versionCode)
    versionName(AppConfig.versionName)

    testInstrumentationRunner = AppConfig.androidTestInstrumentation
    consumerProguardFiles(AppConfig.proguardConsumerRules)
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

      buildConfigField("String", "API_BASE_URL", "\"https://empresas.ioasys.com.br\"")
    }
    getByName("debug") {
      buildConfigField("String", "API_BASE_URL", "\"https://empresas.ioasys.com.br\"")
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

dependencies {
  api(project(":domain"))
  implementation(AppDependencies.Kotlin.kotlinLibraries)
  implementation(AppDependencies.AndroidX.appLibraries)
  implementation(AppDependencies.Koin.koinLibraries)
  implementation(AppDependencies.SquareUp.Retrofit2.retrofitLibraries)
  implementation(AppDependencies.SquareUp.Gson.gsonLibraries)
  implementation(AppDependencies.SquareUp.Okhttp3.okhttpLibraries)
  implementation(AppDependencies.SecurityCrypto.cryptoLibraries)
  testImplementation(AppDependencies.TestLibs.testLibraries)
  androidTestImplementation(AppDependencies.TestLibs.androidTestLibraries)
}