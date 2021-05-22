package br.com.faustfelipe.android.data.api.models.response

data class ResponseWithHeaders<Data: Any, Headers: Any>(
  val data: Data,
  val headers: Headers
)