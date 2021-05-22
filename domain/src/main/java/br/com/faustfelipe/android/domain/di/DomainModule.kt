package br.com.faustfelipe.android.domain.di

import br.com.faustfelipe.android.domain.usecases.SignInUseCase
import org.koin.dsl.module

val usesCasesModule = module {
  factory<SignInUseCase> {
    SignInUseCase(
      repository = get()
    )
  }
}
