package br.com.faustfelipe.android.domain.usecases

import br.com.faustfelipe.android.domain.repository.CompaniesysRepository

class SplashUseCase(
  private val repository: CompaniesysRepository
) {

  fun signIntoApp(): Boolean {
    return repository.signIntoApp()
  }

}