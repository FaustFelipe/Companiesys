package br.com.faustfelipe.android.companiesys.presentation.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onTextChanged(func: (String) -> Unit) {
  addTextChangedListener(object : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
      func(s.toString())
    }

    override fun beforeTextChanged(
      s: CharSequence?,
      start: Int,
      count: Int,
      after: Int
    ) {
    }

    override fun onTextChanged(
      s: CharSequence?,
      start: Int,
      before: Int,
      count: Int
    ) {
    }
  })
}
