package br.com.faustfelipe.android.companiesys.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

  protected val _loading = MutableLiveData<Boolean>().apply {
    value = false
  }
  val loading: LiveData<Boolean> = _loading
  protected val _errorMessage = MutableLiveData<String>()
  val errorMessage: LiveData<String> = _errorMessage
  protected val _relogin = MutableLiveData<Boolean>().apply {
    value = false
  }
  val relogin: LiveData<Boolean> = _relogin

}