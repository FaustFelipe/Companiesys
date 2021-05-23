package br.com.faustfelipe.android.data.api.datasource

import br.com.faustfelipe.android.data.api.models.response.SignInResponse
import br.com.faustfelipe.android.data.api.models.UserPayload
import br.com.faustfelipe.android.data.api.models.response.EnterprisesSearchResponse
import br.com.faustfelipe.android.data.api.models.response.ResponseWithHeaders
import okhttp3.Headers

interface RemoteDataSource {
  suspend fun postSignIn(userPayload: UserPayload): ResponseWithHeaders<SignInResponse, Headers>
  suspend fun getSearchEnterprise(
    accesstoken: String,
    client: String,
    uid: String,
    queryName: String
  ): EnterprisesSearchResponse
}