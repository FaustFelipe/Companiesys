package br.com.faustfelipe.android.domain.utils

object Regex {
  val EMAIL = """^\S+@\S+(\.[\S]+)*$""".toRegex()
}