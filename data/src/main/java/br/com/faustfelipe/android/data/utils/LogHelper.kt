package br.com.faustfelipe.android.data.utils

import android.util.Log
import br.com.faustfelipe.android.data.BuildConfig

object LogHelper {
  fun e(tag: String = TAG, message: String) {
    if (BuildConfig.DEBUG) {
      Log.e(tag, message)
    }
  }
  fun w(tag: String = TAG, message: String) {
    if (BuildConfig.DEBUG) {
      Log.w(tag, message)
    }
  }
  fun i(tag: String = TAG, message: String) {
    if (BuildConfig.DEBUG) {
      Log.i(tag, message)
    }
  }
  fun d(tag: String = TAG, message: String) {
    if (BuildConfig.DEBUG) {
      Log.d(tag, message)
    }
  }
  fun v(tag: String = TAG, message: String) {
    if (BuildConfig.DEBUG) {
      Log.v(tag, message)
    }
  }
  private const val TAG = "LogHelper"
}