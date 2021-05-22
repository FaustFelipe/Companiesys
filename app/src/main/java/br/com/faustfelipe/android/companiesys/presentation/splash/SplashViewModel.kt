package br.com.faustfelipe.android.companiesys.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.faustfelipe.android.companiesys.common.BaseViewModel
import br.com.faustfelipe.android.domain.usecases.SplashUseCase

class SplashViewModel(
  private val splashUseCase: SplashUseCase
): BaseViewModel() {

  private val _signIntoApp = MutableLiveData<Boolean>()
  val signIntoApp: LiveData<Boolean> = _signIntoApp

  fun signIntoApp() {
    _signIntoApp.postValue(splashUseCase.signIntoApp())
  }

}