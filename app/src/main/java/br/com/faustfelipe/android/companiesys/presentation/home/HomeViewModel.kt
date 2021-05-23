package br.com.faustfelipe.android.companiesys.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.faustfelipe.android.companiesys.common.BaseViewModel
import br.com.faustfelipe.android.domain.models.Enterprise
import br.com.faustfelipe.android.domain.usecases.HomeUseCase
import br.com.faustfelipe.android.domain.utils.Result
import kotlinx.coroutines.launch

class HomeViewModel(
  private val homeUseCase: HomeUseCase
): BaseViewModel() {

  private val TAG = this.javaClass.simpleName

  private val _enterpriseData = MutableLiveData<List<Enterprise>>()
  val enterpriseData: LiveData<List<Enterprise>> = _enterpriseData

  private val _searchTerm = MutableLiveData<String>()
  val searchTerm: LiveData<String> = _searchTerm

  fun isEmptyList(): Boolean {
    return _enterpriseData.value?.isNullOrEmpty() ?: true
  }

  fun searchEnterprise(queryName: String) {
    _searchTerm.value = queryName
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
      } else {
        _enterpriseData.postValue(emptyList())
      }
    }
  }

}