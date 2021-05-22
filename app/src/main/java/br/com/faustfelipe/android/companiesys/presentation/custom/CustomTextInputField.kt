package br.com.faustfelipe.android.companiesys.presentation.custom

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatEditText
import br.com.faustfelipe.android.companiesys.R
import br.com.faustfelipe.android.companiesys.presentation.extensions.onTextChanged

class CustomTextInputField @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

  val field: AppCompatEditText

  init {
    val view = inflate(context, R.layout.ly_custom_text_input_field, this)

    field = view.findViewById(R.id.field)
  }

  fun setData(
    @StringRes hint: Int,
    @DrawableRes drawableStart: Int,
    fieldType: FieldType,
    fieldAction: FieldAction
  ) {
    field.apply {
      setHint(hint)
      inputType = fieldType.value
      imeOptions = fieldAction.value
      setCompoundDrawablesWithIntrinsicBounds(drawableStart, 0, 0, 0)
    }
  }

  inline fun onFieldTextChangeListener(crossinline listener: (String) -> Unit) {
    field.onTextChanged {
      listener.invoke(it)
    }
  }

  enum class FieldType(val value: Int) {
    TEXT(InputType.TYPE_CLASS_TEXT),
    EMAIL(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS),
    PASSWORD(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD),
    NUMBER(InputType.TYPE_CLASS_NUMBER)
  }

  enum class FieldAction(val value: Int) {
    NEXT(EditorInfo.IME_ACTION_NEXT),
    DONE(EditorInfo.IME_ACTION_DONE),
    SEARCH(EditorInfo.IME_ACTION_SEARCH)
  }
}