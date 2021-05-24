package br.com.faustfelipe.android.domain.usecases

import br.com.faustfelipe.android.domain.models.Enterprise
import br.com.faustfelipe.android.domain.repository.CompaniesysRepository
import br.com.faustfelipe.android.domain.utils.Result.Error
import br.com.faustfelipe.android.domain.utils.Result.Success
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.Random

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeUseCaseTest {

  private val repository = mock<CompaniesysRepository>()

  private lateinit var useCase: HomeUseCase

  @Before
  fun `before each test`() {
    useCase = HomeUseCase(repository)
  }

  @Test
  fun `should return a list of enterprises when typed search term exists in remote DB`() =
    runBlockingTest {
      val searchTerm = "limited"
      val expected = Success(
        listOf(
          Enterprise(
            id = ID,
            enterpriseName = NAME,
            description = DESCRIPTION,
            enterpriseType = TYPE,
            country = COUNTRY,
            photo = PHOTO
          )
        )
      )

      whenever(repository.searchEnterprise(any())).thenReturn(
        Success(
          listOf(
            Enterprise(
              id = ID,
              enterpriseName = NAME,
              description = DESCRIPTION,
              enterpriseType = TYPE,
              country = COUNTRY,
              photo = PHOTO
            )
          )
        )
      )

      val result = useCase.searchEnterprise(searchTerm)

      verify(repository).searchEnterprise(searchTerm)
      verifyNoMoreInteractions(repository)

      assertEquals(expected, result)
    }

  @Test
  fun `should return an empty list when typed search term doesn't exist in DB`() = runBlockingTest {
    val searchTerm = "fluor"
    val expected = Success(listOf<Enterprise>())

    whenever(repository.searchEnterprise(any())).thenReturn(Success(listOf<Enterprise>()))

    val result = useCase.searchEnterprise(searchTerm)

    verify(repository).searchEnterprise(searchTerm)
    verifyNoMoreInteractions(repository)

    assertEquals(expected, result)
  }

  @Test
  fun `returns access error to the app when token is invalid`() = runBlockingTest {
    val searchTerm = "fluor"
    val expected = Error("Login error", true)

    whenever(repository.searchEnterprise(any())).thenReturn(Error("Login error", true))

    val result = useCase.searchEnterprise(searchTerm)

    verify(repository).searchEnterprise(searchTerm)
    verify(repository).clearLocalData()
    verifyNoMoreInteractions(repository)

    assertEquals(expected, result)
  }

  @Test
  fun `returns error when the app gets an error`() = runBlockingTest {
    val searchTerm = "fluor"
    val expected = Error("App error", false)

    whenever(repository.searchEnterprise(any())).thenReturn(Error("App error", false))

    val result = useCase.searchEnterprise(searchTerm)

    verify(repository).searchEnterprise(searchTerm)
    verifyNoMoreInteractions(repository)

    assertEquals(expected, result)
  }

  companion object {
    private val ID = Random().nextLong()
    private const val NAME = "name"
    private const val DESCRIPTION = "description"
    private const val TYPE = "type"
    private const val COUNTRY = "country"
    private const val PHOTO = "photo"
  }
}