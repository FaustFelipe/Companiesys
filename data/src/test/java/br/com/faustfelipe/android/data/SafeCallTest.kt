package br.com.faustfelipe.android.data

import br.com.faustfelipe.android.data.utils.safeCall
import br.com.faustfelipe.android.data.utils.safeIOCall
import br.com.faustfelipe.android.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import br.com.faustfelipe.android.data.api.utils.LoginException
import kotlin.coroutines.ContinuationInterceptor

class SafeCallTest {

  @Test
  fun `should check if coroutine is running on IO dispatcher`() {
    runBlocking {
      safeIOCall {
        assertEquals(Dispatchers.IO, coroutineContext[ContinuationInterceptor])
        Result.Success(Unit)
      }
    }
  }

  @Test
  fun `should check if coroutine is running on the dispatcher specifeid`() {
    runBlocking {
      safeCall(Dispatchers.Default) {
        assertEquals(Dispatchers.Default, coroutineContext[ContinuationInterceptor])
        Result.Success(Unit)
      }
    }
  }

  @Test
  fun `should return success if the call was success`() {
    runBlocking {
      val result = safeCall(Dispatchers.Default) {
        Result.Success(Unit)
      }
      assertEquals(Result.Success(Unit), result)
    }
  }

  @Test
  fun `should return Error if the call was AppException`() {
    val appError: () -> Result<Unit> = { throw LoginException(listOf("login exception")) }
    runBlocking {
      val result = safeCall(Dispatchers.Default) {
        appError()
      }
      assertEquals(Result.Error("login exception", true), result)
    }
  }

  @Test
  fun `should return Error if the call was Exception`() {
    val genericError: () -> Result<Unit> = { throw Exception("app error") }
    runBlocking {
      val result = safeCall(Dispatchers.Default) {
        genericError()
      }
      assertEquals(Result.Error("app error", false), result)
    }
  }

}