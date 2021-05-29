package br.com.faustfelipe.android.companiesys

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.faustfelipe.android.companiesys.presentation.home.HomeViewModel
import br.com.faustfelipe.android.domain.models.Enterprise
import br.com.faustfelipe.android.domain.usecases.HomeUseCase
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
class HomeViewModelTest {

  @get:Rule
  val taskExecutorRule = InstantTaskExecutorRule()

  private val dispatcher = TestCoroutineDispatcher()

  private lateinit var viewModel: HomeViewModel
  private val mockedHomeUseCase = mock<HomeUseCase>()

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
    viewModel = HomeViewModel(mockedHomeUseCase)
  }

  @After
  fun `after each test`() {
    Dispatchers.resetMain()
  }

  @Test
  fun `check if list of enterprises is empty`() {
    val expected = true

    whenever(mockedHomeUseCase.getListOfEnterprises())
      .thenReturn(emptyList())

    val result = viewModel.isEmptyList()

    assertEquals(expected, result)
  }

  @Test
  fun `check if list of enterprises is not empty`() {
    val expected = false

    whenever(mockedHomeUseCase.getListOfEnterprises())
      .thenReturn(listOf(enterprise))

    val result = viewModel.isEmptyList()

    assertEquals(expected, result)
  }

  /**
   * When running tests e.g for ViewModel that launch coroutines you are most likely to fall into
   * the following exception

  java.lang.IllegalStateException: Module with the Main dispatcher had failed to initialize.
  For tests, Dispatchers.setMain from kotlinx-coroutines-test module can be used

  The reason behind this is the lack of Looper.getMainLooper() on the testing environment which is
  present on a real application. To fix this you need to swap the Main dispatcher
  with TestCoroutineDispatcher
   */
  @Test
  fun `should show error when an error was returned from useCase`() {
    val expected = Result.Error("error", false)

    runBlockingTest {
      whenever(mockedHomeUseCase.searchEnterprise(any()))
        .thenReturn(Result.Error("error", false))

      viewModel.searchEnterprise(QUERY_ENTERPRISE)

      viewModel.errorMessage.observeForever {
        assertEquals(expected.error, it)
      }
      viewModel.relogin.observeForever {
        assertEquals(expected.relogin, it)
      }

      verify(mockedHomeUseCase, times(1)).searchEnterprise(QUERY_ENTERPRISE)
    }
  }

  @Test
  fun `should return a credentials error and then relogin equals to true`() {
    val expected = Result.Error("error", true)

    runBlockingTest {
      whenever(mockedHomeUseCase.searchEnterprise(any()))
        .thenReturn(Result.Error("error", true))

      viewModel.searchEnterprise(QUERY_ENTERPRISE)

      viewModel.errorMessage.observeForever {
        assertEquals(expected.error, it)
      }
      viewModel.relogin.observeForever {
        assertEquals(expected.relogin, it)
      }

      verify(mockedHomeUseCase, times(1)).searchEnterprise(QUERY_ENTERPRISE)
    }
  }

  @Test
  fun `should return a list of enterprises if query search isn't empty`() {
    val expected = listOf(enterprise)

    runBlockingTest {
      whenever(mockedHomeUseCase.searchEnterprise(any()))
        .thenReturn(Result.Success(listOf(enterprise)))

      viewModel.searchEnterprise(QUERY_ENTERPRISE)

      viewModel.enterpriseData.observeForever {
        assertEquals(expected, it)
      }

      verify(mockedHomeUseCase, times(1)).searchEnterprise(QUERY_ENTERPRISE)
    }
  }

  @Test
  fun `should return an empty list if query search is empty`() {
    val expected = listOf<Enterprise>()

    runBlockingTest {
      whenever(mockedHomeUseCase.searchEnterprise(any()))
        .thenReturn(Result.Success(emptyList()))

      viewModel.searchEnterprise(EMPTY_QUERY_ENTERPRISE)

      viewModel.enterpriseData.observeForever {
        assertEquals(expected, it)
      }

      verify(mockedHomeUseCase, times(0)).searchEnterprise(EMPTY_QUERY_ENTERPRISE)
    }
  }

  companion object {
    private const val QUERY_ENTERPRISE = "limited"
    private const val EMPTY_QUERY_ENTERPRISE = ""
  }

}