package br.com.faustfelipe.android.data.api.datasource

import br.com.faustfelipe.android.data.api.models.response.SignInResponse
import br.com.faustfelipe.android.data.api.models.UserPayload

interface RemoteDataSource {
  suspend fun postSignIn(userPayload: UserPayload): SignInResponse
}