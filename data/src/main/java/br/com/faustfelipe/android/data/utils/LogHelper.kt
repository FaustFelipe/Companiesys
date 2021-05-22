package br.com.faustfelipe.android.data.utils

import android.util.Log
import org.koin.android.BuildConfig

object LogHelper {
  fun error(message: String) {
    if (BuildConfig.DEBUG) {
      Log.e(TAG, message)
    }
  }
  fun warning(message: String) {
    if (BuildConfig.DEBUG) {
      Log.w(TAG, message)
    }
  }
  fun information(message: String) {
    if (BuildConfig.DEBUG) {
      Log.i(TAG, message)
    }
  }
  fun debug(message: String) {
    if (BuildConfig.DEBUG) {
      Log.d(TAG, message)
    }
  }
  fun verbose(message: String) {
    if (BuildConfig.DEBUG) {
      Log.v(TAG, message)
    }
  }
  private const val TAG = "LogHelper"
}