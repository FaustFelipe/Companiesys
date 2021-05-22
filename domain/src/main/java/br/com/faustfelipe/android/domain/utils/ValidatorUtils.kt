package br.com.faustfelipe.android.domain.utils

object ValidatorUtils {
  fun isValidEmail(email: String): Boolean {
    return Regex.EMAIL.matches(email)
  }
}