package br.com.faustfelipe.android.domain.models

data class Enterprise(
  val enterpriseName: String,
  val description: String,
  val enterpriseType: String,
  val country: String,
  val photo: String
)