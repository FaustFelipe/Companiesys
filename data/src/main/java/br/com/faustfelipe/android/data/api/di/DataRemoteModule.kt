package br.com.faustfelipe.android.data.api.di

import br.com.faustfelipe.android.data.BuildConfig
import br.com.faustfelipe.android.data.api.CompaniesysAPI
import br.com.faustfelipe.android.data.api.datasource.RemoteDataSource
import br.com.faustfelipe.android.data.api.datasource.RemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {
  factory { providesOkHttpClient() }
  single { createWebService<CompaniesysAPI>(
    okHttpClient = get(),
    url =  BuildConfig.API_BASE_URL
  ) }

  factory<RemoteDataSource> { RemoteDataSourceImpl(serverApi = get()) }
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
  return HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
  }
}

fun providesOkHttpClient(): OkHttpClient {
  val builder = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
  if (BuildConfig.DEBUG) {
    builder.addInterceptor(provideLoggingInterceptor())
  }
  return builder.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
  return Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(url)
    .client(okHttpClient)
    .build()
    .create(T::class.java)
}
