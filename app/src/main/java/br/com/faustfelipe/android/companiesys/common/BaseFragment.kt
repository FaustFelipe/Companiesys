package br.com.faustfelipe.android.companiesys.common

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import br.com.faustfelipe.android.companiesys.R

abstract class BaseFragment(@LayoutRes res: Int): Fragment(res) {

  protected var navControler: NavController? = null

  abstract fun setupView()
  abstract fun setupActions()
  abstract fun setupObservables()

  protected fun navigate(@IdRes res: Int) {
    navControler?.apply {
      navigate(
        res,
        null,
        getDefaultNavOptions(res).build()
      )
    }
  }

  private fun getDefaultNavOptions(@IdRes resId: Int): NavOptions.Builder {
    val options = NavOptions.Builder()
      .setEnterAnim(R.anim.nav_default_enter_anim)
      .setExitAnim(R.anim.nav_default_exit_anim)
      .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
      .setPopExitAnim(R.anim.nav_default_pop_exit_anim)

    navControler?.let { navC ->
      with(navC) {
        currentDestination?.getAction(resId)?.navOptions?.let {
          options.setPopUpTo(it.popUpTo, it.isPopUpToInclusive)
        }
      }
    }

    return options
  }

}