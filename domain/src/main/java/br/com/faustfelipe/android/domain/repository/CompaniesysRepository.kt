package br.com.faustfelipe.android.domain.repository

import br.com.faustfelipe.android.domain.models.Enterprise
import br.com.faustfelipe.android.domain.utils.Result

interface CompaniesysRepository {
  fun signIntoApp(): Boolean
  suspend fun postSignIn(email: String, password: String): Result<Unit>
  suspend fun searchEnterprise(queryName: String): Result<List<Enterprise>>
  fun clearLocalData()
}