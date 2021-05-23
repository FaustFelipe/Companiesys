package br.com.faustfelipe.android.companiesys.presentation.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun View.makeInvisible() {
  this.visibility = View.INVISIBLE
}

fun View.makeVisible() {
  this.visibility = View.VISIBLE
}

fun View.makeGone() {
  this.visibility = View.GONE
}

fun View.setVisibilityGoneForBoolean(visible: Boolean) {
  if (visible) makeVisible() else makeGone()
}

fun View.setVisibilityInvisibleForBoolean(visible: Boolean) {
  if (visible) makeVisible() else makeInvisible()
}

fun ViewGroup.inflate(@LayoutRes view: Int): View {
  return LayoutInflater.from(this.context).inflate(view, this, false)
}
