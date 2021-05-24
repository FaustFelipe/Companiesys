package br.com.faustfelipe.android.domain.usecases

import br.com.faustfelipe.android.domain.models.Enterprise
import br.com.faustfelipe.android.domain.repository.CompaniesysRepository
import br.com.faustfelipe.android.domain.utils.Result

class HomeUseCase(
  private val repository: CompaniesysRepository
) {

  suspend fun searchEnterprise(queryName: String): Result<List<Enterprise>> {
    return when(val result = repository.searchEnterprise(queryName)) {
      is Result.Success -> result
      is Result.Error -> {
        if (result.relogin) {
          repository.clearLocalData()
        }
        result
      }
    }
  }

}