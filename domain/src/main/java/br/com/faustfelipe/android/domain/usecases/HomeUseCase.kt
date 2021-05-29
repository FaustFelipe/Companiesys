package br.com.faustfelipe.android.domain.usecases

import br.com.faustfelipe.android.domain.models.Enterprise
import br.com.faustfelipe.android.domain.repository.CompaniesysRepository
import br.com.faustfelipe.android.domain.utils.Result

class HomeUseCase(
  private val repository: CompaniesysRepository
) {

  private val listOfEnterprises = mutableListOf<Enterprise>()

  fun getListOfEnterprises(): List<Enterprise> = listOfEnterprises

  suspend fun searchEnterprise(queryName: String): Result<List<Enterprise>> {
    return when(val result = repository.searchEnterprise(queryName)) {
      is Result.Success -> {
        listOfEnterprises.clear()
        listOfEnterprises.addAll(result.data)
        result
      }
      is Result.Error -> {
        if (result.relogin) {
          repository.clearLocalData()
        }
        result
      }
    }
  }

}