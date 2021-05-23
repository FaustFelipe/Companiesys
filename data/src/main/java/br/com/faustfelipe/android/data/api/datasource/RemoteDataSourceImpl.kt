package br.com.faustfelipe.android.data.api.datasource

import br.com.faustfelipe.android.data.api.CompaniesysAPI
import br.com.faustfelipe.android.data.api.models.response.SignInResponse
import br.com.faustfelipe.android.data.api.models.UserPayload
import br.com.faustfelipe.android.data.api.models.response.EnterpriseSearchResponse
import br.com.faustfelipe.android.data.api.models.response.EnterprisesSearchResponse
import br.com.faustfelipe.android.data.api.models.response.ResponseWithHeaders
import br.com.faustfelipe.android.data.api.utils.ApiException
import br.com.faustfelipe.android.data.api.utils.callService
import br.com.faustfelipe.android.data.utils.LogHelper
import okhttp3.Headers
import retrofit2.Response

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

  override suspend fun getSearchEnterprise(
    accesstoken: String,
    client: String,
    uid: String,
    queryName: String
  ): EnterprisesSearchResponse {
    return callService {
      serverApi.getSearchEnterprises(
        contentType = "application/json",
        accessToken = accesstoken,
        client = client,
        uid = uid,
        queryName = queryName
      )
    }
  }

  override suspend fun getEnterprise(
    accesstoken: String,
    client: String,
    uid: String,
    id: String
  ): EnterpriseSearchResponse {
    return callService {
      serverApi.getShowEnterprise(
        contentType = "application/json",
        accessToken = accesstoken,
        client = client,
        uid = uid,
        id = id
      )
    }
  }
}