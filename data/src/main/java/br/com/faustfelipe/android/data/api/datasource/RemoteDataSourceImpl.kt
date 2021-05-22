package br.com.faustfelipe.android.data.api.datasource

import br.com.faustfelipe.android.data.api.CompaniesysAPI
import br.com.faustfelipe.android.data.api.models.response.SignInResponse
import br.com.faustfelipe.android.data.api.models.UserPayload
import br.com.faustfelipe.android.data.api.models.response.ResponseWithHeaders
import br.com.faustfelipe.android.data.api.utils.ApiException
import br.com.faustfelipe.android.data.utils.LogHelper
import okhttp3.Headers

class RemoteDataSourceImpl(
  private val serverApi: CompaniesysAPI
) : RemoteDataSource {
  override suspend fun postSignIn(userPayload: UserPayload): ResponseWithHeaders<SignInResponse, Headers> {
    val response = serverApi.postSignIn(userPayload)
    return if (response.isSuccessful) {
      ResponseWithHeaders(response.body()!!, response.headers())
    } else {
      LogHelper.e(tag = this.javaClass.simpleName, message = "Api Exception")
      throw ApiException()
    }
  }
}