package br.com.faustfelipe.android.domain.repository

import br.com.faustfelipe.android.domain.utils.Result

interface CompaniesysRepository {
  suspend fun postSignIn(email: String, password: String): Result<Unit>
}