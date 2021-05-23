package br.com.faustfelipe.android.data.api.mappers

import br.com.faustfelipe.android.data.api.models.response.Enterprises
import br.com.faustfelipe.android.data.api.models.response.EnterprisesSearchResponse
import br.com.faustfelipe.android.domain.models.Enterprise

object EnterpriseMapper {
  fun map(enterpriseSearchResponse: EnterprisesSearchResponse) =
    enterpriseSearchResponse.enterprisesList.map {
      map(it)
    }

  private fun map(enterprises: Enterprises) = Enterprise(
    enterpriseName = enterprises.enterpriseName ?: "",
    description = enterprises.description ?: "",
    enterpriseType = enterprises.enterprisetype?.enterpriseTypeName ?: "",
    photo = enterprises.photo ?: "",
    country = enterprises.country ?: ""
  )
}