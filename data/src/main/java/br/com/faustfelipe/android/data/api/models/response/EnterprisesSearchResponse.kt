package br.com.faustfelipe.android.data.api.models.response

import com.google.gson.annotations.SerializedName

data class EnterprisesSearchResponse(
  @SerializedName("enterprises") val enterprisesList: List<Enterprises>
)

data class Enterprises(
  @SerializedName("id") val id: Long?,
  @SerializedName("email_enterprise") val emailEnterprise: String?,
  @SerializedName("facebook") val facebook: String?,
  @SerializedName("twitter") val twitter: String?,
  @SerializedName("linkedin") val linkedin: String?,
  @SerializedName("phone") val phone: String?,
  @SerializedName("own_enterprise") val ownEnterprise: Boolean?,
  @SerializedName("enterprise_name") val enterpriseName: String?,
  @SerializedName("photo") val photo: String?,
  @SerializedName("description") val description: String?,
  @SerializedName("city") val city: String?,
  @SerializedName("country") val country: String?,
  @SerializedName("value") val value: Long?,
  @SerializedName("share_price") val sharePrice: String?,
  @SerializedName("enterprise_type") val enterprisetype: EnterpriseType?
)

data class EnterpriseType(
  @SerializedName("id") val id: Long?,
  @SerializedName("enterprise_type_name") val enterpriseTypeName: String?
)
