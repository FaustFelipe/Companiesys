package br.com.faustfelipe.android.domain.utils

import br.com.faustfelipe.android.domain.utils.Result.Error
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T : Any> safeIOCall(call: suspend CoroutineScope.() -> Result<T>): Result<T> {
  return safeCall(Dispatchers.IO, call)
}

suspend fun <T : Any> safeCall(
  coroutineContext: CoroutineContext,
  call: suspend CoroutineScope.() -> Result<T>
): Result<T> = withContext(coroutineContext) {
  try {
    call()
  } catch (e: AppException) {
    Error(emptyList())
  } catch (e: Exception) {
    Error(emptyList())
  }
}