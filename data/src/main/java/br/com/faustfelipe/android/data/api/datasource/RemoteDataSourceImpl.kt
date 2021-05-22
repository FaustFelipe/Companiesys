package br.com.faustfelipe.android.data.api.datasource

import br.com.faustfelipe.android.data.api.CompaniesysAPI
import br.com.faustfelipe.android.data.api.models.response.SignInResponse
import br.com.faustfelipe.android.data.api.models.UserPayload
import br.com.faustfelipe.android.data.api.utils.callService

class RemoteDataSourceImpl(
  private val serverApi: CompaniesysAPI
): RemoteDataSource {
  override suspend fun postSignIn(userPayload: UserPayload): SignInResponse = callService {
    serverApi.postSignIn(userPayload)
  }
}