package br.com.faustfelipe.android.domain.utils

import java.lang.Exception

abstract class AppException: Exception() {
  abstract fun message(): String
}
