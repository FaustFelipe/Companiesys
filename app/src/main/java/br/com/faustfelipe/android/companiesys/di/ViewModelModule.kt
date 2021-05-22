package br.com.faustfelipe.android.companiesys.di

import br.com.faustfelipe.android.companiesys.presentation.signin.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel<SignInViewModel> {
    SignInViewModel(
      signInUseCase = get()
    )
  }
}