package br.com.faustfelipe.android.domain.usecases

import br.com.faustfelipe.android.domain.repository.CompaniesysRepository
import br.com.faustfelipe.android.domain.utils.Result

class SignInUseCase(
  private val repository: CompaniesysRepository
) {
  suspend fun signIn(email: String, password: String): Result<Unit> {
    return when(val response = repository.postSignIn(email, password)) {
      is Result.Success -> Result.Success(Unit)
      is Result.Error -> response
    }
  }
}