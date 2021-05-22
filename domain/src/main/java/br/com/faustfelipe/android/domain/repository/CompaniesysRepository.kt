package br.com.faustfelipe.android.domain.repository

import br.com.faustfelipe.android.domain.utils.Result

interface CompaniesysRepository {
  fun signIntoApp(): Boolean
  suspend fun postSignIn(email: String, password: String): Result<Unit>
}