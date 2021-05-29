package br.com.faustfelipe.android.companiesys

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.faustfelipe.android.companiesys.presentation.home.HomeViewModel
import br.com.faustfelipe.android.domain.models.Enterprise
import br.com.faustfelipe.android.domain.usecases.HomeUseCase
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

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

  @get:Rule
  val taskExecutorRule = InstantTaskExecutorRule()

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
    viewModel = HomeViewModel(mockedHomeUseCase)
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

  companion object {
    private const val QUERY_ENTERPRISE = "limited"
  }

}