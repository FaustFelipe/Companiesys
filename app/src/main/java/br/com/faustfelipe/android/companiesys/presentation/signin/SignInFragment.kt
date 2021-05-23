package br.com.faustfelipe.android.companiesys.presentation.signin

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.fragment.findNavController
import br.com.faustfelipe.android.companiesys.R
import br.com.faustfelipe.android.companiesys.common.BaseFragment
import br.com.faustfelipe.android.companiesys.presentation.custom.CustomTextInputField
import br.com.faustfelipe.android.companiesys.presentation.custom.CustomTextInputField.FieldAction.DONE
import br.com.faustfelipe.android.companiesys.presentation.custom.CustomTextInputField.FieldAction.NEXT
import br.com.faustfelipe.android.companiesys.presentation.custom.CustomTextInputField.FieldType.EMAIL
import br.com.faustfelipe.android.companiesys.presentation.custom.CustomTextInputField.FieldType.PASSWORD
import br.com.faustfelipe.android.companiesys.presentation.extensions.setVisibilityGoneForBoolean
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_sign_in.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

  private val viewModel: SignInViewModel by viewModel()

  private val inputEmail: CustomTextInputField get() = input_email
  private val inputPassword: CustomTextInputField get() = input_password
  private val txtErrorCredentials: AppCompatTextView get() = txt_error_credentials
  private val btnSignIn: MaterialButton get() = btn_sign_in
  private val loadingView: FrameLayout get() = loading_view

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
      onFieldTextChangeListener { enableButton() }
    }

    inputPassword.apply {
      onFieldTextChangeListener { enableButton() }
    }

    btnSignIn.apply {
      setOnClickListener {
        viewModel.signIn(inputEmail.getData(), inputPassword.getData())
      }
    }
  }

  override fun setupObservables() {
    viewModel.apply {
      loading.observe(viewLifecycleOwner, {
        loadingView.setVisibilityGoneForBoolean(it)
        btnSignIn.isEnabled = !it
      })

      emailError.observe(viewLifecycleOwner, {
        if (it) inputEmail.showError(getString(R.string.sign_in_invalid_email))
        else inputEmail.showError(null)
      })

      passwordError.observe(viewLifecycleOwner, {
        if (it) inputPassword.showError(getString(R.string.sign_in_invalid_password))
        else inputEmail.showError(null)
      })

      invalidCredentials.observe(viewLifecycleOwner, {
        txtErrorCredentials.setVisibilityGoneForBoolean(it)
      })

      navigateToFeed.observe(viewLifecycleOwner, {
        navigate(R.id.action_signInFragment_to_homeFragment)
      })
    }
  }

  private fun enableButton() {
    val emailNotEmpty = inputEmail.getData().isNotEmpty()
    val passwordNotEmpty = inputPassword.getData().isNotEmpty()
    btnSignIn.isEnabled = emailNotEmpty && passwordNotEmpty
  }

}