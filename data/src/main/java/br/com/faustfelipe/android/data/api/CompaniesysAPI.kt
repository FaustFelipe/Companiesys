package br.com.faustfelipe.android.data.api

import br.com.faustfelipe.android.data.api.models.UserPayload
import br.com.faustfelipe.android.data.api.models.response.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CompaniesysAPI {
  @POST("/api/v1/users/auth/sign_in")
  suspend fun postSignIn(@Body userPayload: UserPayload): Response<SignInResponse>
}