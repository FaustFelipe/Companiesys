package br.com.faustfelipe.android.companiesys

import android.app.Application
import br.com.faustfelipe.android.companiesys.di.viewModelModule
import br.com.faustfelipe.android.data.dataModule
import br.com.faustfelipe.android.data.api.di.remoteDataSourceModule
import br.com.faustfelipe.android.domain.di.usesCasesModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class CompaniesysApp : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      koin.loadModules(
        listOf(viewModelModule, dataModule, remoteDataSourceModule, usesCasesModule)
      )
    }
  }

  override fun onTerminate() {
    super.onTerminate()

    stopKoin()
  }
}