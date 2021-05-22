package br.com.faustfelipe.android.companiesys.presentation.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import br.com.faustfelipe.android.companiesys.R
import br.com.faustfelipe.android.companiesys.common.BaseFragment

class HomeFragment : BaseFragment(R.layout.fragment_home) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    navControler = findNavController()

    setupView()
    setupActions()
    setupObservables()
  }

  override fun setupView() {
  }

  override fun setupActions() {
  }

  override fun setupObservables() {
  }
}