package br.com.faustfelipe.android.companiesys.presentation.signin

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import br.com.faustfelipe.android.companiesys.R
import br.com.faustfelipe.android.companiesys.common.BaseFragment
import br.com.faustfelipe.android.companiesys.presentation.custom.CustomTextInputField
import br.com.faustfelipe.android.companiesys.presentation.custom.CustomTextInputField.FieldAction.DONE
import br.com.faustfelipe.android.companiesys.presentation.custom.CustomTextInputField.FieldAction.NEXT
import br.com.faustfelipe.android.companiesys.presentation.custom.CustomTextInputField.FieldType.EMAIL
import br.com.faustfelipe.android.companiesys.presentation.custom.CustomTextInputField.FieldType.PASSWORD
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

  private val inputEmail: CustomTextInputField get() = input_email
  private val inputPassword: CustomTextInputField get() = input_password
  private val btnSignIn: MaterialButton get() = btn_sign_in

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    navControler = findNavController()

    setupView()
    setupActions()
    setupObservables()
  }

  override fun setupView() {
    inputEmail.apply {
      setData(
        hint = R.string.sign_in_email,
        drawableStart = R.drawable.ic_email,
        fieldType = EMAIL,
        fieldAction = NEXT
      )
    }

    inputPassword.apply {
      setData(
        hint = R.string.sign_in_password,
        drawableStart = R.drawable.ic_padlock,
        fieldType = PASSWORD,
        fieldAction = DONE
      )
    }
  }

  override fun setupActions() {
    inputEmail.apply {
      onFieldTextChangeListener {  }
    }

    inputPassword.apply {
      onFieldTextChangeListener {  }
    }

    btnSignIn.apply {
      setOnClickListener {  }
    }
  }

  override fun setupObservables() {
  }
}