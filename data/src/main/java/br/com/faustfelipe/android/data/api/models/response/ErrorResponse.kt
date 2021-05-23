package br.com.faustfelipe.android.data.api.models.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
  @SerializedName("errors") var errors: List<String>?
)