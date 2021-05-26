package br.com.faustfelipe.android.data

import br.com.faustfelipe.android.data.api.mappers.EnterpriseMapper
import br.com.faustfelipe.android.data.api.models.response.EnterpriseSearchResponse
import br.com.faustfelipe.android.data.api.models.response.EnterpriseType
import br.com.faustfelipe.android.data.api.models.response.Enterprises
import br.com.faustfelipe.android.data.api.models.response.EnterprisesSearchResponse
import br.com.faustfelipe.android.domain.models.Enterprise
import org.junit.Assert.assertEquals
import org.junit.Test

class EnterpriseMapperTest {

  private val enterprisesSearchResponse = EnterprisesSearchResponse(
    enterprisesList = listOf(
      Enterprises(
        id = 0,
        emailEnterprise = "email@enterprise.com.br",
        facebook = null,
        twitter = null,
        linkedin = null,
        phone = null,
        ownEnterprise = null,
        enterpriseName = "Enterprise",
        photo = null,
        description = "A description",
        city = null,
        country = "BR",
        value = null,
        sharePrice = null,
        enterprisetype = EnterpriseType(id = 1, enterpriseTypeName = "Type")
      )
    )
  )
  private val enterpriseSearchResponse = EnterpriseSearchResponse(
    enterprise = Enterprises(
      id = 0,
      emailEnterprise = "email@enterprise.com.br",
      facebook = null,
      twitter = null,
      linkedin = null,
      phone = null,
      ownEnterprise = null,
      enterpriseName = "Enterprise",
      photo = null,
      description = "A description",
      city = null,
      country = "BR",
      value = null,
      sharePrice = null,
      enterprisetype = EnterpriseType(id = 1, enterpriseTypeName = "Type")
    )
  )
  private val enterprise = Enterprise(
    id = 0,
    enterpriseName = "Enterprise",
    description = "A description",
    enterpriseType = "Type",
    country = "BR",
    photo = BuildConfig.API_BASE_URL
  )

  @Test
  fun `should return an Enterprise Mapped`() {
    val result = EnterpriseMapper.map(enterpriseSearchResponse)
    assertEquals(enterprise, result)
  }

  @Test
  fun `should return a list of Enterprises mapped`() {
    val result = EnterpriseMapper.map(enterprisesSearchResponse)
    assertEquals(listOf(enterprise), result)
  }
}