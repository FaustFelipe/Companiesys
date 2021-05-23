package br.com.faustfelipe.android.data.api.utils

import br.com.faustfelipe.android.data.api.models.response.ErrorResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

suspend fun <T> callService(call: suspend () -> Response<T>): T {
  val response = call.invoke()
  return when {
    response.isSuccessful -> {
      response.body()!!
    }
    response.code() == 401 -> {
      val gson = Gson()
      val type = object : TypeToken<ErrorResponse>() {}.type
      val errorResponse = gson.fromJson<ErrorResponse>(response.errorBody()?.charStream(), type)
      throw LoginException(errorResponse.errors ?: emptyList())
    }
    else -> {
      throw ApiException()
    }
  }
}

