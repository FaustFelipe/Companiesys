package br.com.faustfelipe.android.data

import br.com.faustfelipe.android.data.api.datasource.RemoteDataSource
import br.com.faustfelipe.android.data.api.models.UserPayload
import br.com.faustfelipe.android.domain.repository.CompaniesysRepository
import br.com.faustfelipe.android.domain.utils.Result
import br.com.faustfelipe.android.domain.utils.Result.Success
import br.com.faustfelipe.android.domain.utils.safeIOCall

class ComaniesysRepositoryImpl(
  private val dataSource: RemoteDataSource
): CompaniesysRepository {
  override suspend fun postSignIn(email: String, password: String): Result<Unit> {
    return safeIOCall {
      val userPayload = UserPayload(email, password)
      val response = dataSource.postSignIn(userPayload)
      Success(Unit)
    }
  }
}