package br.com.faustfelipe.android.data

import br.com.faustfelipe.android.data.api.utils.ApiException
import br.com.faustfelipe.android.data.api.utils.LoginException
import br.com.faustfelipe.android.data.api.utils.callService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class CallRetrofitServiceTest {

  @Test
  fun `should return a valid response body`() {
    runBlockingTest {
      val response = callService { getSuccessResponse() }
      assertEquals(Unit, response)
    }
  }

  @Test
  fun `should return a LoginException when the credentials are invalid`() {
    runBlockingTest {
      val expected = "Invalid credentials"
      val response = try {
        callService { getLoginErrorResponse() }
      } catch (e: Exception) {
        assert(e is LoginException)
        (e as LoginException).message()
      }
      assertEquals(expected, response)
    }
  }

  @Test
  fun `should return an ApiException with some error`() {
    runBlockingTest {
      val expected = "Error connecting to API"
      val result = try {
        callService { getErrorResponse() }
      } catch (e: Exception) {
        assert(e is ApiException)
        (e as ApiException).message()
      }
      assertEquals(expected, result)
    }
  }

  private fun getSuccessResponse(): Response<Unit> {
    return Response.success(Unit)
  }

  private fun getLoginErrorResponse(): Response<Unit> {
    return Response.error(
      401,
      CREDENTIALS_LOGIN_ERROR.toResponseBody("application/json".toMediaTypeOrNull())
    )
  }

  private fun getErrorResponse(): Response<Unit> {
    return Response.error(
      422,
      API_ERROR.toResponseBody("application/json".toMediaTypeOrNull())
    )
  }

  companion object {
    private const val CREDENTIALS_LOGIN_ERROR = "{\"errors\":[\"Invalid credentials\"]}"
    private const val API_ERROR = "{\"errors\":[\"API error\"]}"
  }
}