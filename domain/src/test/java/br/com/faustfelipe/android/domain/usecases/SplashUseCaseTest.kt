package br.com.faustfelipe.android.domain.usecases

import br.com.faustfelipe.android.domain.repository.CompaniesysRepository
import br.com.faustfelipe.android.domain.usecases.SplashUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashUseCaseTest {

  private val repository = mock<CompaniesysRepository>()

  private lateinit var useCase: SplashUseCase

  @Before
  fun `before each test`() {
    useCase = SplashUseCase(repository)
  }

  @Test
  fun `check if token is valid`() {
    val expected = true

    whenever(repository.signIntoApp()).thenReturn(true)

    val result = useCase.signIntoApp()

    verify(repository).signIntoApp()

    assertEquals(expected, result)
  }

  @Test
  fun `check if token is not valid`() {
    val expected = false

    whenever(repository.signIntoApp()).thenReturn(false)

    val result = useCase.signIntoApp()

    verify(repository).signIntoApp()

    assertEquals(expected, result)
  }
}