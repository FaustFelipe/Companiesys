package br.com.faustfelipe.android.companiesys.presentation.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.faustfelipe.android.companiesys.common.BaseViewModel
import br.com.faustfelipe.android.domain.usecases.SignInUseCase
import br.com.faustfelipe.android.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(
  private val signInUseCase: SignInUseCase
): BaseViewModel() {

  private val _emailError = MutableLiveData<Boolean>().apply {
    value = false
  }
  val emailError: LiveData<Boolean> = _emailError

  private val _passwordError = MutableLiveData<Boolean>().apply {
    value = false
  }
  val passwordError: LiveData<Boolean> = _passwordError

  private val _invalidCredentials = MutableLiveData<Boolean>().apply {
    value = false
  }
  val invalidCredentials: LiveData<Boolean> = _invalidCredentials

  private val _navigateToFeed = MutableLiveData<Unit>()
  val navigateToFeed: LiveData<Unit> = _navigateToFeed

  fun signIn(email: String, password: String) {
    clearInvalidCredentialsMessage()

    _loading.postValue(true)
    viewModelScope.launch(Dispatchers.IO) {
      val response = signInUseCase.signIn(email, password)
      if (response is Result.Success) {
        _navigateToFeed.postValue(Unit)
      } else {
        _emailError.postValue(true)
        _passwordError.postValue(true)
        _invalidCredentials.postValue(true)
      }
      _loading.postValue(false)
    }
  }

  private fun clearInvalidCredentialsMessage() {
    _emailError.postValue(false)
    _passwordError.postValue(false)
    _invalidCredentials.postValue(false)
  }

}