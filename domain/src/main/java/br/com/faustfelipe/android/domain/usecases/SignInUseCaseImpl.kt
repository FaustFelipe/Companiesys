package br.com.faustfelipe.android.domain.usecases

import br.com.faustfelipe.android.domain.repository.CompaniesysRepository
import br.com.faustfelipe.android.domain.utils.Result
import br.com.faustfelipe.android.domain.utils.ValidatorUtils

class SignInUseCase(
  private val repository: CompaniesysRepository
) {
  suspend fun signIn(email: String, password: String): Result<Unit> {
    if (!ValidatorUtils.isValidEmail(email))
      return Result.Error("Invalid API credentials", false)

    return when(val response = repository.postSignIn(email, password)) {
      is Result.Success -> Result.Success(Unit)
      is Result.Error -> response
    }
  }
}