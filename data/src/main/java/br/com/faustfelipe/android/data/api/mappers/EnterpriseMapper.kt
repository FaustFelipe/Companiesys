package br.com.faustfelipe.android.data.api.mappers

import br.com.faustfelipe.android.data.BuildConfig
import br.com.faustfelipe.android.data.api.models.response.EnterpriseSearchResponse
import br.com.faustfelipe.android.data.api.models.response.Enterprises
import br.com.faustfelipe.android.data.api.models.response.EnterprisesSearchResponse
import br.com.faustfelipe.android.domain.models.Enterprise

object EnterpriseMapper {
  fun map(enterpriseSearchResponse: EnterprisesSearchResponse) =
    enterpriseSearchResponse.enterprisesList.map {
      map(it)
    }

  fun map(enterpriseSearchResponse: EnterpriseSearchResponse) =
    map(enterpriseSearchResponse.enterprise)

  private fun map(enterprises: Enterprises) = Enterprise(
    id = enterprises.id ?: 0L,
    enterpriseName = enterprises.enterpriseName ?: "",
    description = enterprises.description ?: "",
    enterpriseType = enterprises.enterprisetype?.enterpriseTypeName ?: "",
    photo = "${BuildConfig.API_BASE_URL}${enterprises.photo}" ?: "",
    country = enterprises.country ?: ""
  )
}