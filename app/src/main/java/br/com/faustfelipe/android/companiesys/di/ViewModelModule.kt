package br.com.faustfelipe.android.companiesys.di

import br.com.faustfelipe.android.companiesys.presentation.details.DetailsViewModel
import br.com.faustfelipe.android.companiesys.presentation.home.HomeViewModel
import br.com.faustfelipe.android.companiesys.presentation.signin.SignInViewModel
import br.com.faustfelipe.android.companiesys.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel<SplashViewModel> {
    SplashViewModel(
      splashUseCase = get()
    )
  }

  viewModel<SignInViewModel> {
    SignInViewModel(
      signInUseCase = get()
    )
  }

  viewModel<HomeViewModel> {
    HomeViewModel(
      homeUseCase = get()
    )
  }

  viewModel<DetailsViewModel> {
    DetailsViewModel(
      detailsUseCase = get()
    )
  }
}
