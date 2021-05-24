package br.com.faustfelipe.android.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import br.com.faustfelipe.android.data.api.datasource.RemoteDataSource
import br.com.faustfelipe.android.data.api.mappers.EnterpriseMapper
import br.com.faustfelipe.android.data.api.models.CustomHeaders
import br.com.faustfelipe.android.data.api.models.UserPayload
import br.com.faustfelipe.android.domain.models.Enterprise
import br.com.faustfelipe.android.domain.repository.CompaniesysRepository
import br.com.faustfelipe.android.domain.utils.Result
import br.com.faustfelipe.android.domain.utils.Result.Success
import br.com.faustfelipe.android.domain.utils.safeIOCall
import okhttp3.Headers

private const val ENCRYPTED_PREFS_FILE_NAME = "companiesys_prefs"
private const val ENCRYPTED_PREFS_ACCESS_TOKEN_KEY = "key_prefs_access_token"
private const val ENCRYPTED_PREFS_CLIENT_KEY = "key_prefs_client"
private const val ENCRYPTED_PREFS_UID_KEY = "key_prefs_uid"

class ComaniesysRepositoryImpl(
  private val context: Context,
  private val dataSource: RemoteDataSource
) : CompaniesysRepository {

  private val TAG by lazy {
    this.javaClass.simpleName
  }
  private val sharedPreferences by lazy {
    EncryptedSharedPreferences.create(
      ENCRYPTED_PREFS_FILE_NAME,
      MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
      context,
      EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
      EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
  }

  override fun signIntoApp(): Boolean {
    val accessToken = getHeader(ENCRYPTED_PREFS_ACCESS_TOKEN_KEY)
    val client = getHeader(ENCRYPTED_PREFS_CLIENT_KEY)
    val uid = getHeader(ENCRYPTED_PREFS_UID_KEY)
    return accessToken.isNotEmpty() &&
      client.isNotEmpty() &&
      uid.isNotEmpty()
  }

  override suspend fun postSignIn(email: String, password: String): Result<Unit> {
    return safeIOCall {
      val userPayload = UserPayload(email, password)
      val response = dataSource.postSignIn(userPayload)
      saveCustomHeaders(response.headers)
      Success(Unit)
    }
  }

  override suspend fun searchEnterprise(queryName: String): Result<List<Enterprise>> {
    return safeIOCall {
      val result = dataSource.getSearchEnterprise(
        accesstoken = getHeader(ENCRYPTED_PREFS_ACCESS_TOKEN_KEY),
        client = getHeader(ENCRYPTED_PREFS_CLIENT_KEY),
        uid = getHeader(ENCRYPTED_PREFS_UID_KEY),
        queryName = queryName
      )
      Success(EnterpriseMapper.map(result))
    }
  }

  override suspend fun showEnterprise(id: String): Result<Enterprise> {
    return safeIOCall {
      val result = dataSource.getEnterprise(
        accesstoken = getHeader(ENCRYPTED_PREFS_ACCESS_TOKEN_KEY),
        client = getHeader(ENCRYPTED_PREFS_CLIENT_KEY),
        uid = getHeader(ENCRYPTED_PREFS_UID_KEY),
        id = id
      )
      Success(EnterpriseMapper.map(result))
    }
  }

  override fun clearLocalData() {
    sharedPreferences.edit().clear().apply()
  }

  private fun saveCustomHeaders(headers: Headers) {
    with(sharedPreferences.edit()) {
      putString(ENCRYPTED_PREFS_ACCESS_TOKEN_KEY, headers[CustomHeaders.ACCESS_TOKEN.key])
      putString(ENCRYPTED_PREFS_CLIENT_KEY, headers[CustomHeaders.CLIENT.key])
      putString(ENCRYPTED_PREFS_UID_KEY, headers[CustomHeaders.UID.key])
      apply()
    }
  }

  private fun getHeader(key: String): String {
    return sharedPreferences.getString(key, null) ?: ""
  }
}