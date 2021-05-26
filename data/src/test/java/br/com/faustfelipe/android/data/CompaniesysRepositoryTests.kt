package br.com.faustfelipe.android.data

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class CompaniesysRepositoryTests {

  private val repository = mock<CompaniesysRepositoryImpl>()
  private lateinit var sharedPreferences: SharedPreferences

  @Before
  fun `before each test`() {
    sharedPreferences = FakeEncryptedSharedPreferences()
  }

  @Test
  fun `should return false when headers access wasn't created`() {
    val expected = false

    whenever(repository.signIntoApp()).thenReturn(
      getHeader(ENCRYPTED_PREFS_ACCESS_TOKEN_KEY).isNotEmpty() &&
        getHeader(ENCRYPTED_PREFS_CLIENT_KEY).isNotEmpty() &&
        getHeader(ENCRYPTED_PREFS_UID_KEY).isNotEmpty()
    )

    val result = repository.signIntoApp()

    verify(repository).signIntoApp()
    verifyNoMoreInteractions(repository)

    assertEquals(expected, result)
  }

  @Test
  fun `should return true when headerss access was created`() {
    val expected = true

    (sharedPreferences as? FakeEncryptedSharedPreferences)?.fakeEmpty = false

    whenever(repository.signIntoApp()).thenReturn(
      getHeader(ENCRYPTED_PREFS_ACCESS_TOKEN_KEY).isNotEmpty() &&
        getHeader(ENCRYPTED_PREFS_CLIENT_KEY).isNotEmpty() &&
        getHeader(ENCRYPTED_PREFS_UID_KEY).isNotEmpty()
    )

    val result = repository.signIntoApp()

    verify(repository).signIntoApp()
    verifyNoMoreInteractions(repository)

    assertEquals(expected, result)
  }

  private fun getHeader(key: String): String {
    return sharedPreferences.getString(key, null) ?: ""
  }

  class FakeEncryptedSharedPreferences: SharedPreferences {

    var fakeEmpty: Boolean = true

    override fun getAll(): MutableMap<String, *> {
      TODO("Not yet implemented")
    }

    override fun getString(key: String?, defValue: String?): String? {
      return if (fakeEmpty) "" else "notEmpty"
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String> {
      TODO("Not yet implemented")
    }

    override fun getInt(key: String?, defValue: Int): Int {
      TODO("Not yet implemented")
    }

    override fun getLong(key: String?, defValue: Long): Long {
      TODO("Not yet implemented")
    }

    override fun getFloat(key: String?, defValue: Float): Float {
      TODO("Not yet implemented")
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
      TODO("Not yet implemented")
    }

    override fun contains(key: String?): Boolean {
      TODO("Not yet implemented")
    }

    override fun edit(): Editor {
      TODO("Not yet implemented")
    }

    override fun registerOnSharedPreferenceChangeListener(listener: OnSharedPreferenceChangeListener?) {
      TODO("Not yet implemented")
    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: OnSharedPreferenceChangeListener?) {
      TODO("Not yet implemented")
    }
  }

  companion object {
    private const val ENCRYPTED_PREFS_FILE_NAME = "companiesys_prefs_test"
    private const val ENCRYPTED_PREFS_ACCESS_TOKEN_KEY = "key_token"
    private const val ENCRYPTED_PREFS_CLIENT_KEY = "key_client"
    private const val ENCRYPTED_PREFS_UID_KEY = "key_uid"
  }
}