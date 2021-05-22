package br.com.faustfelipe.android.data.api.datasource

import br.com.faustfelipe.android.data.api.models.response.SignInResponse
import br.com.faustfelipe.android.data.api.models.UserPayload
import br.com.faustfelipe.android.data.api.models.response.ResponseWithHeaders
import okhttp3.Headers

interface RemoteDataSource {
  suspend fun postSignIn(userPayload: UserPayload): ResponseWithHeaders<SignInResponse, Headers>
}