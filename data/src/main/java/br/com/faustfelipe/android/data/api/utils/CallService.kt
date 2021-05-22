package br.com.faustfelipe.android.data.api.utils

import retrofit2.Response

suspend fun <T> callService(call: suspend () -> Response<T>): T {
  val response = call.invoke()
  return if (response.isSuccessful) {
    response.body()!!
  } else {
    throw ApiException()
  }
}

