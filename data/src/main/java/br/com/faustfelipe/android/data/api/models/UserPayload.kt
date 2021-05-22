package br.com.faustfelipe.android.data.api.models

import com.google.gson.annotations.SerializedName

data class UserPayload(
  @SerializedName("email") val email: String,
  @SerializedName("password") val password: String
)