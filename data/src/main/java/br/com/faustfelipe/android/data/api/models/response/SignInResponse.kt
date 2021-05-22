package br.com.faustfelipe.android.data.api.models.response

import com.google.gson.annotations.SerializedName

data class SignInResponse(
  @SerializedName("investor") val investor: Investor,
  @SerializedName("enterprise") val enterprise: Any?,
  @SerializedName("success") val success: Boolean?,
  @SerializedName("errors") val errors: ArrayList<String>?
)

data class Investor(
  @SerializedName("id") val id: Long?,
  @SerializedName("investor_name") val investorName: String?,
  @SerializedName("email") val email: String?,
  @SerializedName("city") val city: String?,
  @SerializedName("country") val country: String?,
  @SerializedName("balance") val balance: Long?,
  @SerializedName("photo") val photo: String?,
  @SerializedName("portfolio") val portfolio: Portfolio?,
  @SerializedName("portfolio_value") val portfolioValue: Long?,
  @SerializedName("first_access") val firstAccess: Boolean?,
  @SerializedName("super_angel") val superAngel: Boolean?
)

data class Portfolio(
  @SerializedName("enterprises_number") val enterprisesNumber: Int?,
  @SerializedName("enterprises") val enterprises: ArrayList<*>?
)
