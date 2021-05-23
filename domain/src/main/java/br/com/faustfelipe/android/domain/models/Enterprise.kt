package br.com.faustfelipe.android.domain.models

data class Enterprise(
  val id: Long,
  val enterpriseName: String,
  val description: String,
  val enterpriseType: String,
  val country: String,
  val photo: String
) {
  companion object {
    object PARAMS {
      const val ENTERPRISE_ID_PARAM = "enterpriseIdParam"
      const val ENTERPRISE_NAME_PARAM = "enterpriseNameParam"
    }
  }
}