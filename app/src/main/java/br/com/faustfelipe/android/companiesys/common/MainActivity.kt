package br.com.faustfelipe.android.companiesys.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.faustfelipe.android.companiesys.R.layout

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
  }
}