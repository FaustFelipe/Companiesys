package br.com.faustfelipe.android.data.di

import br.com.faustfelipe.android.data.CompaniesysRepositoryImpl
import br.com.faustfelipe.android.domain.repository.CompaniesysRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
  factory<CompaniesysRepository> {
    CompaniesysRepositoryImpl(
      context = androidContext(),
      dataSource = get()
    )
  }
}
