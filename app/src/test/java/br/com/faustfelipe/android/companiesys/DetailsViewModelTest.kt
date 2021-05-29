package br.com.faustfelipe.android.companiesys

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.faustfelipe.android.companiesys.presentation.details.DetailsViewModel
import br.com.faustfelipe.android.domain.models.Enterprise
import br.com.faustfelipe.android.domain.usecases.DetailsUseCase
import br.com.faustfelipe.android.domain.utils.Result
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {

  @get:Rule
  val taskExecutorRule = InstantTaskExecutorRule()

  private val dispatcher = TestCoroutineDispatcher()

  private lateinit var viewModel: DetailsViewModel
  private val mockedDetailsUseCase = mock<DetailsUseCase>()

  private val enterprise =
    Enterprise(
      id = 0,
      enterpriseName = "",
      description = "",
      enterpriseType = "",
      country = "",
      photo = ""
    )

  @Before
  fun `before each test`() {
    Dispatchers.setMain(dispatcher)
    viewModel = DetailsViewModel(mockedDetailsUseCase)
  }

  @After
  fun `after each test`() {
    Dispatchers.resetMain()
  }

  @Test
  fun `should return an enterprise when id exists`() {
    val expected = Result.Success(enterprise)

    runBlockingTest {
      whenever(mockedDetailsUseCase.showEnterprise(any()))
        .thenReturn(Result.Success(enterprise.copy()))

      viewModel.showEnterprise(ENTERPRISE_ID)

      viewModel.enterprise.observeForever {
        assertEquals(expected.data, it)
      }

      verify(mockedDetailsUseCase, times(1)).showEnterprise(ENTERPRISE_ID)
    }
  }

  @Test
  fun `should return an error if some exception occurred in useCase`() {
    val expected = Result.Error("error", false)

    runBlockingTest {
      whenever(mockedDetailsUseCase.showEnterprise(any()))
        .thenReturn(Result.Error("error", false))

      viewModel.showEnterprise(ENTERPRISE_ID)

      viewModel.errorMessage.observeForever {
        assertEquals(expected.error, it)
      }
      viewModel.relogin.observeForever {
        assertEquals(expected.relogin, it)
      }

      verify(mockedDetailsUseCase, times(1)).showEnterprise(ENTERPRISE_ID)
    }
  }

  @Test
  fun `should return an error and relogin user if credentials are invalid`() {
    val expected = Result.Error("error", true)

    runBlockingTest {
      whenever(mockedDetailsUseCase.showEnterprise(any()))
        .thenReturn(Result.Error("error", true))

      viewModel.showEnterprise(ENTERPRISE_ID)

      viewModel.errorMessage.observeForever {
        assertEquals(expected.error, it)
      }
      viewModel.relogin.observeForever {
        assertEquals(expected.relogin, it)
      }

      verify(mockedDetailsUseCase, times(1)).showEnterprise(ENTERPRISE_ID)
    }
  }

  companion object {
    private const val ENTERPRISE_ID = 1L
  }

}