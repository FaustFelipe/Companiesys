package br.com.faustfelipe.android.companiesys.presentation.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import br.com.faustfelipe.android.companiesys.R
import br.com.faustfelipe.android.companiesys.common.BaseFragment
import br.com.faustfelipe.android.data.utils.LogHelper
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home), SearchView.OnQueryTextListener,
  MenuItem.OnActionExpandListener {

  private val TAG by lazy {
    this.javaClass.simpleName
  }
  private val viewModel: HomeViewModel by viewModel()

  private var searchView: SearchView? = null

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    navControler = findNavController()

    setupView()
    setupActions()
    setupObservables()
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.menu_home, menu)

    val searchItem = menu.findItem(R.id.action_search)
    searchItem?.setOnActionExpandListener(this)
    searchView = searchItem?.actionView as SearchView
    searchView?.run {
      queryHint = getString(R.string.hint_search)
      setOnQueryTextListener(this@HomeFragment)
    }

  }

  override fun setupView() {
    toolbarView = toolbar
    setHasOptionsMenu(true)
    setupToolbar()

  }

  override fun setupActions() {
  }

  override fun setupObservables() {
    viewModel.apply {
      errorMessage.observe(viewLifecycleOwner, {
        Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
      })
      relogin.observe(viewLifecycleOwner, {
        if (it) navigate(R.id.action_homeFragment_to_signInFragment)
      })
      enterpriseData.observe(viewLifecycleOwner, {

      })
    }
  }

  override fun onQueryTextSubmit(query: String?): Boolean {
    LogHelper.d(TAG, "Submit query")
    return true
  }

  override fun onQueryTextChange(newText: String?): Boolean {
    LogHelper.d(TAG, newText ?: "")
    viewModel.searchEnterprise(newText ?: "")
    return true
  }

  override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
    return true
  }

  override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
    return true
  }
}