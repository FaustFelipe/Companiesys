package br.com.faustfelipe.android.companiesys.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.faustfelipe.android.companiesys.common.BaseViewModel
import br.com.faustfelipe.android.data.utils.LogHelper
import br.com.faustfelipe.android.domain.models.Enterprise
import br.com.faustfelipe.android.domain.usecases.HomeUseCase
import br.com.faustfelipe.android.domain.utils.Result
import kotlinx.coroutines.launch

class HomeViewModel(
  private val homeUseCase: HomeUseCase
): BaseViewModel() {

  private val TAG = this.javaClass.simpleName

  private val _errorMessage = MutableLiveData<String>()
  val errorMessage: LiveData<String> = _errorMessage

  private val _relogin = MutableLiveData<Boolean>().apply {
    value = false
  }
  val relogin: LiveData<Boolean> = _relogin

  private val _enterpriseData = MutableLiveData<List<Enterprise>>().apply {
    value = emptyList()
  }
  val enterpriseData: LiveData<List<Enterprise>> = _enterpriseData

  fun searchEnterprise(queryName: String) {
    viewModelScope.launch {
      if (queryName.isNotEmpty()) {
        _loading.postValue(true)
        when(val result = homeUseCase.searchEnterprise(queryName)) {
          is Result.Success -> _enterpriseData.postValue(result.data)
          is Result.Error -> {
            _errorMessage.postValue(result.error)
            _relogin.postValue(result.relogin)
          }
        }
        _loading.postValue(false)
      }
    }
  }

}