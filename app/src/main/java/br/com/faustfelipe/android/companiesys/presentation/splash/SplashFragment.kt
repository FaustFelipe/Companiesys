package br.com.faustfelipe.android.companiesys.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import br.com.faustfelipe.android.companiesys.R
import br.com.faustfelipe.android.companiesys.common.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

  private val viewModel: SplashViewModel by viewModel()

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
      viewModel.signIntoApp()
    }, DELAY_OPEN_LOGIN)
  }

  override fun setupObservables() {
    viewModel.apply {
      signIntoApp.observe(viewLifecycleOwner, {
        if (it) navigate(R.id.action_splashFragment_to_homeFragment)
        else navigate(R.id.action_splashFragment_to_signInFragment)
      })
    }
  }

  companion object {
    private const val DELAY_OPEN_LOGIN = 1500L
  }

}