package br.com.faustfelipe.android.data

import br.com.faustfelipe.android.domain.repository.CompaniesysRepository
import org.koin.dsl.module

val dataModule = module {
  factory<CompaniesysRepository> {
    ComaniesysRepositoryImpl(
      dataSource = get()
    )
  }
}
