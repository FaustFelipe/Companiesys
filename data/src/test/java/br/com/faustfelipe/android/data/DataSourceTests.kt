package br.com.faustfelipe.android.data

import br.com.faustfelipe.android.data.api.datasource.RemoteDataSource
import br.com.faustfelipe.android.data.api.models.UserPayload
import br.com.faustfelipe.android.data.api.models.response.EnterpriseSearchResponse
import br.com.faustfelipe.android.data.api.models.response.Enterprises
import br.com.faustfelipe.android.data.api.models.response.EnterprisesSearchResponse
import br.com.faustfelipe.android.data.api.models.response.Investor
import br.com.faustfelipe.android.data.api.models.response.ResponseWithHeaders
import br.com.faustfelipe.android.data.api.models.response.SignInResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.Headers
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class DataSourceTests {

  private val dataSource = mock<RemoteDataSource>()

  @Test
  fun `sign in user, return data and headers`() = runBlockingTest {
    val email = VALID_EMAIL
    val password = VALID_PASSWORD
    val userPayload = UserPayload(email, password)

    val expected = RESPONSE_WITH_HEADERS

    whenever(dataSource.postSignIn(any())).thenReturn(
      RESPONSE_WITH_HEADERS.copy()
    )

    val result = dataSource.postSignIn(userPayload)

    verify(dataSource).postSignIn(userPayload)

    assertEquals(expected, result)
  }

  @Test
  fun `should return an empty list from service when user types a term that doesn't exists in the DB`() =
    runBlockingTest {
      val querySearch = "listing"
      val expected = EnterprisesSearchResponse(enterprisesList = listOf())

      whenever(dataSource.getSearchEnterprise(any(), any(), any(), any())).thenReturn(
        EnterprisesSearchResponse(enterprisesList = listOf())
      )

      val result = dataSource.getSearchEnterprise(
        accesstoken = VALID_ACCESS_TOKEN,
        client = VALID_CLIENT,
        uid = VALID_UID,
        queryName = querySearch
      )

      verify(dataSource).getSearchEnterprise(
        accesstoken = VALID_ACCESS_TOKEN,
        client = VALID_CLIENT,
        uid = VALID_UID,
        queryName = querySearch
      )

      assertEquals(expected, result)
    }

  @Test
  fun `should return a list of enterprises when user types a term that exists in the DB`() =
    runBlockingTest {
      val querySearch = "limited"
      val expected = ENTERPRISES_RESPONSE

      whenever(dataSource.getSearchEnterprise(any(), any(), any(), any())).thenReturn(
        ENTERPRISES_RESPONSE.copy()
      )

      val result = dataSource.getSearchEnterprise(
        VALID_ACCESS_TOKEN,
        VALID_CLIENT,
        VALID_UID,
        querySearch
      )

      verify(dataSource).getSearchEnterprise(
        VALID_ACCESS_TOKEN,
        VALID_CLIENT,
        VALID_UID,
        querySearch
      )

      assertEquals(expected, result)
    }

  @Test
  fun `should return an enterprise detail`() = runBlockingTest {
    val expected = ENTERPRISE_RESPONSE

    whenever(dataSource.getEnterprise(any(), any(), any(), any()))
      .thenReturn(ENTERPRISE_RESPONSE.copy())

    val result = dataSource.getEnterprise(
      VALID_ACCESS_TOKEN,
      VALID_CLIENT,
      VALID_UID,
      id = "9"
    )

    verify(dataSource).getEnterprise(
      VALID_ACCESS_TOKEN,
      VALID_CLIENT,
      VALID_UID,
      id = "9"
    )

    assertEquals(expected, result)
  }

  companion object {
    private const val VALID_EMAIL = "testeapple@ioasys.com.br"
    private const val VALID_PASSWORD = "12341234"
    private const val VALID_ACCESS_TOKEN = "validAccessToken"
    private const val VALID_CLIENT = "validClient"
    private const val VALID_UID = "validUid"
    private val ENTERPRISES_RESPONSE = EnterprisesSearchResponse(
      enterprisesList = listOf(
        Enterprises(
          id = 1,
          emailEnterprise = null,
          facebook = null,
          twitter = null,
          linkedin = null,
          phone = null,
          ownEnterprise = null,
          enterpriseName = "name",
          photo = "photo",
          country = "country",
          description = "description",
          value = null,
          city = "city",
          enterprisetype = null,
          sharePrice = null
        )
      )
    )
    private val ENTERPRISE_RESPONSE = EnterpriseSearchResponse(
      enterprise = Enterprises(
        id = 1,
        emailEnterprise = null,
        facebook = null,
        twitter = null,
        linkedin = null,
        phone = null,
        ownEnterprise = null,
        enterpriseName = "name",
        photo = "photo",
        country = "country",
        description = "description",
        value = null,
        city = "city",
        enterprisetype = null,
        sharePrice = null
      )
    )
    private val RESPONSE_WITH_HEADERS = ResponseWithHeaders<SignInResponse, Headers>(
      SignInResponse(
        investor = Investor(
          id = null,
          investorName = "name",
          email = "email",
          city = "city",
          country = "country",
          balance = null,
          photo = "photo",
          portfolio = null,
          portfolioValue = null,
          firstAccess = null,
          superAngel = null
        ),
        enterprise = null,
        success = true,
        errors = null
      ),
      Headers.Builder()
        .add("Content-Type", "application/json")
        .add("access-token", VALID_ACCESS_TOKEN)
        .add("client", VALID_CLIENT)
        .add("uid", VALID_UID)
        .build()
    )
  }
}