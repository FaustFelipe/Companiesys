package br.com.faustfelipe.android.domain.usecases

import br.com.faustfelipe.android.domain.models.Enterprise
import br.com.faustfelipe.android.domain.repository.CompaniesysRepository
import br.com.faustfelipe.android.domain.utils.Result
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
class DetailsUseCaseTest {

  private val repository = mock<CompaniesysRepository>()

  private lateinit var useCase: DetailsUseCase

  @Before
  fun `before each test`() {
    useCase = DetailsUseCase(repository)
  }

  @Test
  fun `returns the object if the call to the API was successful`() = runBlockingTest {
    val expected = Result.Success(Enterprise(
      id = ID,
      enterpriseName = NAME,
      description = DESCRIPTION,
      enterpriseType = TYPE,
      country = COUNTRY,
      photo = PHOTO
    ))
    whenever(repository.showEnterprise(any())).thenReturn(Result.Success(Enterprise(
      id = ID,
      enterpriseName = NAME,
      description = DESCRIPTION,
      enterpriseType = TYPE,
      country = COUNTRY,
      photo = PHOTO
    )))

    val result = useCase.showEnterprise(ID)

    verify(repository).showEnterprise(ID.toString())
    verifyNoMoreInteractions(repository)

    assertEquals(expected, result)
  }

  @Test
  fun `returns access error to the app when token is invalid`() = runBlockingTest {
    val expected = Result.Error("Login error", true)

    whenever(repository.showEnterprise(any())).thenReturn(Result.Error("Login error", true))

    val result = useCase.showEnterprise(ID)

    verify(repository).showEnterprise(ID.toString())
    verify(repository).clearLocalData()
    verifyNoMoreInteractions(repository)

    assertEquals(expected, result)
  }

  @Test
  fun `returns error when the app gets an error`() = runBlockingTest {
    val expected = Result.Error("App error", false)

    whenever(repository.showEnterprise(any())).thenReturn(Result.Error("App error", false))

    val result = useCase.showEnterprise(ID)

    verify(repository).showEnterprise(ID.toString())
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