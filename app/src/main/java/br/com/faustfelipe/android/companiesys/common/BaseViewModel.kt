package br.com.faustfelipe.android.companiesys.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

  val loading = MutableLiveData<Boolean>().apply {
    value = false
  }

}