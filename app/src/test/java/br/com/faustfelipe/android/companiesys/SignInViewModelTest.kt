package br.com.faustfelipe.android.companiesys

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.faustfelipe.android.companiesys.presentation.signin.SignInViewModel
import br.com.faustfelipe.android.domain.usecases.SignInUseCase
import br.com.faustfelipe.android.domain.utils.Result
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.times

@OptIn(ExperimentalCoroutinesApi::class)
class SignInViewModelTest {

  @get:Rule
  val taskExecutorRule = InstantTaskExecutorRule()

  private val mockedSignInUseCase = mock<SignInUseCase>()
  private lateinit var viewModel: SignInViewModel

  @Before
  fun `before each test`() {
    viewModel = SignInViewModel(mockedSignInUseCase)
  }

  @Test
  fun `should return an email error and password error and invalid credentials observable`() {
    runBlockingTest {
      whenever(mockedSignInUseCase.signIn(any(), any()))
        .thenReturn(Result.Error("Invalid API credentials", false))

      viewModel.signIn(INVALID_EMAIL, PASSWORD)

      verify(mockedSignInUseCase, times(1)).signIn(any(), any())

      viewModel.apply {
        navigateToFeed.observeForever {
          assertEquals(false, it)
        }
        emailError.observeForever {
          assertEquals(true, it)
        }
        passwordError.observeForever {
          assertEquals(true, it)
        }
        invalidCredentials.observeForever {
          assertEquals(true, it)
        }
      }
    }
  }

  @Test
  fun `should return success with a valid email`() {
    runBlockingTest {
      whenever(mockedSignInUseCase.signIn(any(), any()))
        .thenReturn(Result.Success(Unit))

      viewModel.signIn(VALID_EMAIL, PASSWORD)

      verify(mockedSignInUseCase).signIn(any(), any())

      viewModel.apply {
        navigateToFeed.observeForever {
          assertEquals(Unit, it)
        }
        emailError.observeForever {
          assertEquals(false, it)
        }
        passwordError.observeForever {
          assertEquals(false, it)
        }
        invalidCredentials.observeForever {
          assertEquals(false, it)
        }
      }
    }
  }

  companion object {
    private const val VALID_EMAIL = "testeapple@ioasys.com.br"
    private const val INVALID_EMAIL = "testeapple@.com"
    private const val PASSWORD = "123456"
  }

}
