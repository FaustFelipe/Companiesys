package br.com.faustfelipe.android.domain.utils

import org.junit.Test
import org.assertj.core.api.Java6Assertions.assertThat

class ValidatorUtilsTest {

  @Test
  fun `should return false for invalid email`() {
    val email = "testapple@.com.br"
    assertThat(ValidatorUtils.isValidEmail(email)).isFalse
  }

  @Test
  fun `should return true for valid email`() {
    val email = "testeapple@ioasys.com.br"
    assertThat(ValidatorUtils.isValidEmail(email)).isTrue
  }

}