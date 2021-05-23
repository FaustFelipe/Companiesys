package br.com.faustfelipe.android.data.api

import br.com.faustfelipe.android.data.api.models.UserPayload
import br.com.faustfelipe.android.data.api.models.response.EnterprisesSearchResponse
import br.com.faustfelipe.android.data.api.models.response.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface CompaniesysAPI {
  @POST("$PREFIX_API/users/auth/sign_in")
  suspend fun postSignIn(@Body userPayload: UserPayload): Response<SignInResponse>

  @GET("$PREFIX_API/enterprises")
  suspend fun getSearchEnterprises(
    @Header("Content-Type") contentType: String,
    @Header("access-token") accessToken: String,
    @Header("client") client: String,
    @Header("uid") uid: String,
    @Query("name") queryName: String
  ): Response<EnterprisesSearchResponse>

  companion object {
    private const val PREFIX_API = "/api/v1"
  }
}