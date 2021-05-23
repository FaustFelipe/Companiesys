package br.com.faustfelipe.android.companiesys.presentation.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(url: String) {
  Glide
    .with(this.context)
    .load(url)
    .centerCrop()
    .into(this)
}
