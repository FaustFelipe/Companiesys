import Versions.AndroidX.Navigation

object Versions {
  object Material {
    const val material = "1.2.1"
  }

  object AppLevel {
    const val gradle = "4.2.0"
    const val kotlin = "1.5.0"
  }

  object Kotlin {
    const val coroutines = "1.4.2"
  }

  object AndroidX {
    const val appcompat = "1.2.0"
    const val coreKtx = "1.3.2"
    const val constraintLayout = "2.0.4"

    object Lifecycle {
      const val version = "2.2.0"
    }

    object Navigation {
      const val version = "2.3.2"
    }
  }

  object Koin {
    const val version = "2.1.6"
  }

  object SquareUp {
    object Retrofit2 {
      const val retrofit = "2.9.0"
    }

    object Okhttp3 {
      const val okhttp = "4.9.0"
    }

    object Gson {
      const val gson = "2.9.0"
    }
  }

  object SecurityCrypto {
    const val crypto = "1.0.0"
  }

  object Bumptech {
    const val glide = "4.12.0"
  }

  object Test {
    const val androidArchTest = "2.0.0"
    const val assertj = "3.11.1"
    const val jUnit = "4.+"
    const val mockitoKotlin = "2.0.0"
    const val coroutineTest = "1.4.2"
  }
}