package br.com.faustfelipe.android.data.api.utils

import br.com.faustfelipe.android.domain.utils.AppException

class ApiException: AppException() {
  override fun message(): String {
    return "Error connecting to API"
  }
}