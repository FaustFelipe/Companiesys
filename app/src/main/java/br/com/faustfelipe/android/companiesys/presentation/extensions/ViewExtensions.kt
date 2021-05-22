package br.com.faustfelipe.android.companiesys.presentation.extensions

import android.view.View

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
