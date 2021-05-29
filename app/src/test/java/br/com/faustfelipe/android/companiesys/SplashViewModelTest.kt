package br.com.faustfelipe.android.companiesys

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.faustfelipe.android.companiesys.presentation.splash.SplashViewModel
import br.com.faustfelipe.android.domain.usecases.SplashUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashViewModelTest {

  @get:Rule
  val taskExecutorRule = InstantTaskExecutorRule()

  private lateinit var viewModel: SplashViewModel

  private val mockedSplashUseCase = mock<SplashUseCase>()

  @Before
  fun `before each test`() {
    viewModel = SplashViewModel(mockedSplashUseCase)
  }

  @Test
  fun `should return false`() {
    // Given
    val loggedIn = false
    // When
    whenever(mockedSplashUseCase.signIntoApp()).thenReturn(loggedIn)
    // then
    viewModel.signIntoApp.observeForever {
      assertFalse(it)
    }
    viewModel.signIntoApp()
  }

  @Test
  fun `should return true`() {
    // Given
    val loggedIn = true
    // When
    whenever(mockedSplashUseCase.signIntoApp()).thenReturn(loggedIn)
    // Then
    viewModel.signIntoApp.observeForever {
      assertTrue(it)
    }
    viewModel.signIntoApp()
  }

}