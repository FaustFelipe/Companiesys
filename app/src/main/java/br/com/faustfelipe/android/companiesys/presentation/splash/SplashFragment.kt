package br.com.faustfelipe.android.companiesys.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import br.com.faustfelipe.android.companiesys.R
import br.com.faustfelipe.android.companiesys.common.BaseFragment

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

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
    Handler(Looper.getMainLooper()).postDelayed({
      navigate(R.id.action_splashFragment_to_signInFragment)
    }, DELAY_OPEN_LOGIN)
  }

  override fun setupObservables() {
  }

  companion object {
    private const val DELAY_OPEN_LOGIN = 1500L
  }

}