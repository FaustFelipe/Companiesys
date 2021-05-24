package br.com.faustfelipe.android.domain.usecases

import br.com.faustfelipe.android.domain.repository.CompaniesysRepository
import br.com.faustfelipe.android.domain.utils.Result.Error
import br.com.faustfelipe.android.domain.utils.Result.Success
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SignInUseCaseTest {

  private val repository = mock<CompaniesysRepository>()

  private lateinit var useCase: SignInUseCase

  @Before
  fun `before each test`() {
    useCase = SignInUseCase(repository)
  }

  @Test
  fun `Return API success when user types valid login credentials`() = runBlockingTest {
    val expected = Success(Unit)

    whenever(repository.postSignIn(any(), any())).thenReturn(Success(Unit))

    val result = useCase.signIn(EMAIL, PASSWORD)

    verify(repository).postSignIn(EMAIL, PASSWORD)

    assertEquals(expected, result)
  }

  @Test
  fun `Return API error when user types invalid login credentials`() = runBlockingTest {
    val expected = Error("Error connecting to API", false)

    whenever(repository.postSignIn(any(), any())).thenReturn(Error("Error connecting to API", false))

    val result = useCase.signIn(INVALID_EMAIL, PASSWORD)

    verify(repository).postSignIn(INVALID_EMAIL, PASSWORD)

    assertEquals(expected, result)
  }

  companion object {
    private const val EMAIL = "testeapple@ioasys.com.br"
    private const val INVALID_EMAIL = "testeapple@ioasys.com"
    private const val PASSWORD = "12341234"
  }

}