package br.com.faustfelipe.android.domain.usecases

import br.com.faustfelipe.android.domain.models.Enterprise
import br.com.faustfelipe.android.domain.repository.CompaniesysRepository
import br.com.faustfelipe.android.domain.utils.Result

class DetailsUseCase(
  private val repository: CompaniesysRepository
) {

  suspend fun showEnterprise(id: Long): Result<Enterprise> {
    return when(val result = repository.showEnterprise(id.toString())) {
      is Result.Success -> result
      is Result.Error -> {
        repository.clearLocalData()
        result
      }
    }
  }

}