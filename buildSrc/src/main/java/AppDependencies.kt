import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
  object Material {
    const val material = "com.google.android.material:material:${Versions.Material.material}"
  }

  object Kotlin {
    private val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.AppLevel.kotlin}"
    private val coroutines =
      "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"

    val kotlinLibraries = arrayListOf<String>().apply {
      add(kotlinStdLib)
      add(coroutines)
    }
  }

  object AndroidX {
    private val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
    private val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}"
    private val constraintLayout =
      "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"

    val appLibraries = arrayListOf<String>().apply {
      add(appcompat)
      add(coreKtx)
      add(constraintLayout)
    }

    object Lifecycle {
      private val extensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidX.Lifecycle.version}"
      private val common =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.AndroidX.Lifecycle.version}"
      private val viewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.Lifecycle.version}"
      private val liveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.Lifecycle.version}"
      private val viewModelSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.AndroidX.Lifecycle.version}"

      val lifecycleLibraries = arrayListOf<String>().apply {
        add(extensions)
        add(common)
        add(viewModelKtx)
        add(liveDataKtx)
        add(viewModelSavedState)
      }
    }

    object Navigation {
      private val fragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.Navigation.version}"
      private val uiKtx =
        "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.Navigation.version}"
      private val dynamicFeatures =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.AndroidX.Navigation.version}"

      val navigationLibraries = arrayListOf<String>().apply {
        add(fragmentKtx)
        add(uiKtx)
      }
    }
  }

  object Koin {
    private val android = "org.koin:koin-android:${Versions.Koin.version}"
    private val androidViewModel = "org.koin:koin-androidx-viewmodel:${Versions.Koin.version}"

    val koinLibraries = arrayListOf<String>().apply {
      add(android)
    }

    val viewModelKoinLibraries = arrayListOf<String>().apply {
      add(androidViewModel)
    }
  }

  object SquareUp {
    object Retrofit2 {
      private val retrofit = "com.squareup.retrofit2:retrofit:${Versions.SquareUp.Retrofit2.retrofit}"

      val retrofitLibraries = arrayListOf<String>().apply {
        add(retrofit)
      }
    }

    object Okhttp3 {
      private val okhttp = "com.squareup.okhttp3:okhttp:${Versions.SquareUp.Okhttp3.okhttp}"
      private val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.SquareUp.Okhttp3.okhttp}"

      val okhttpLibraries = arrayListOf<String>().apply {
        add(okhttp)
        add(loggingInterceptor)
      }
    }

    object Gson {
      private val gson = "com.squareup.retrofit2:converter-gson:${Versions.SquareUp.Gson.gson}"

      val gsonLibraries = arrayListOf<String>().apply {
        add(gson)
      }
    }
  }

  object SecurityCrypto {
    private val crypto = "androidx.security:security-crypto:${Versions.SecurityCrypto.crypto}"

    val cryptoLibraries = arrayListOf<String>().apply {
      add(crypto)
    }
  }

  object TestLibs {
    private val jUnit = "junit:junit:${Versions.Test.jUnit}"
    private val extJunit = "androidx.test.ext:junit:${Versions.Test.extJunit}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Test.espressoCore}"
    private val navTest = "androidx.navigation:navigation-testing:${Versions.Test.navigation}"

    val testLibraries = arrayListOf<String>().apply {
      add(jUnit)
    }
    val androidTestLibraries = arrayListOf<String>().apply {
      add(extJunit)
      add(espressoCore)
      add(navTest)
    }
  }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
  list.forEach { dependency ->
    add("kapt", dependency)
  }
}

fun DependencyHandler.implementation(list: List<String>) {
  list.forEach { dependency ->
    add("implementation", dependency)
  }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
  list.forEach { dependency ->
    add("androidTestImplementation", dependency)
  }
}

fun DependencyHandler.testImplementation(list: List<String>) {
  list.forEach { dependency ->
    add("testImplementation", dependency)
  }
}
