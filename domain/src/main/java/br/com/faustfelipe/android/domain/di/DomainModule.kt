package br.com.faustfelipe.android.domain.di

import br.com.faustfelipe.android.domain.usecases.SignInUseCase
import br.com.faustfelipe.android.domain.usecases.SplashUseCase
import org.koin.dsl.module

val usesCasesModule = module {
  factory<SplashUseCase> {
    SplashUseCase(
      repository = get()
    )
  }

  factory<SignInUseCase> {
    SignInUseCase(
      repository = get()
    )
  }
}
