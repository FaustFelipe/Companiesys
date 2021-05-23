package br.com.faustfelipe.android.companiesys.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.faustfelipe.android.companiesys.common.BaseViewModel
import br.com.faustfelipe.android.domain.models.Enterprise
import br.com.faustfelipe.android.domain.usecases.DetailsUseCase
import br.com.faustfelipe.android.domain.utils.Result
import kotlinx.coroutines.launch

class DetailsViewModel(
  private val detailsUseCase: DetailsUseCase
): BaseViewModel() {

  private val _enterprise = MutableLiveData<Enterprise>()
  val enterprise: LiveData<Enterprise> = _enterprise

  fun showEnterprise(id: Long) {
    viewModelScope.launch {
      _loading.postValue(true)
      when(val result = detailsUseCase.showEnterprise(id)) {
        is Result.Success -> _enterprise.postValue(result.data)
        is Result.Error -> {
          _errorMessage.postValue(result.error)
          _relogin.postValue(result.relogin)
        }
      }
      _loading.postValue(false)
    }
  }

}