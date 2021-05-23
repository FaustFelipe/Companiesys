package br.com.faustfelipe.android.data.api.utils

import br.com.faustfelipe.android.domain.utils.AppException

class LoginException(
  private val errors: List<String>
): AppException() {
  override fun relogin(): Boolean {
    return true
  }
  override fun message(): String {
    return errors.firstOrNull() ?: ""
  }
}