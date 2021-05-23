package br.com.faustfelipe.android.companiesys.presentation.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.faustfelipe.android.companiesys.R
import br.com.faustfelipe.android.companiesys.common.BaseFragment
import br.com.faustfelipe.android.companiesys.presentation.adapter.SearchEnterpriseAdapter
import br.com.faustfelipe.android.companiesys.presentation.extensions.makeGone
import br.com.faustfelipe.android.companiesys.presentation.extensions.makeVisible
import br.com.faustfelipe.android.companiesys.presentation.extensions.setVisibilityGoneForBoolean
import br.com.faustfelipe.android.data.utils.LogHelper
import br.com.faustfelipe.android.domain.models.Enterprise
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val SEARCH_ENTERPRISE_DELAY = 300L

class HomeFragment : BaseFragment(R.layout.fragment_home), SearchView.OnQueryTextListener,
  MenuItem.OnActionExpandListener {

  private val TAG by lazy {
    this.javaClass.simpleName
  }
  private val viewModel: HomeViewModel by viewModel()

  private var searchView: SearchView? = null
  private val txtInfoStartSearching: AppCompatTextView get() = txt_info_start_searching
  private val txtNoSearchResults: AppCompatTextView get() = txt_no_search_results
  private val recyclerSearchEnterprise: RecyclerView get() = recycler_search_enterprise

  private val searchEnterpriseAdapter: SearchEnterpriseAdapter =
    SearchEnterpriseAdapter(::showDetails)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    navControler = findNavController()

    setupView()
    setupActions()
    setupObservables()
  }

  override fun onResume() {
    super.onResume()
    txtInfoStartSearching.setVisibilityGoneForBoolean(viewModel.isEmptyList())
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

    if (viewModel.searchTerm.value?.isNotEmpty() == true) {
      Handler(Looper.getMainLooper()).post {
        val query = viewModel.searchTerm.value
        searchItem.expandActionView()
        searchView?.run {
          setQuery(query, true)
          clearFocus()
        }
      }
    }
  }

  override fun setupView() {
    toolbarView = toolbar
    setHasOptionsMenu(true)
    setupToolbar()

    recyclerSearchEnterprise.apply {
      adapter = searchEnterpriseAdapter
    }
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
        txtNoSearchResults.setVisibilityGoneForBoolean(it.isEmpty())
        recyclerSearchEnterprise.setVisibilityGoneForBoolean(it.isNotEmpty())
        searchEnterpriseAdapter.setList(it)
      })
    }
  }

  override fun onQueryTextSubmit(query: String?): Boolean {
    LogHelper.i(TAG, "Submit query")
    searchView?.clearFocus()
    return true
  }

  override fun onQueryTextChange(newText: String?): Boolean {
    LogHelper.d(TAG, newText ?: "")
    Handler(Looper.getMainLooper()).postDelayed({
      viewModel.searchEnterprise(newText ?: "")
    }, SEARCH_ENTERPRISE_DELAY)
    return true
  }

  override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
    txtInfoStartSearching.makeGone()
    return true
  }

  override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
    txtInfoStartSearching.makeVisible()
    txtNoSearchResults.makeGone()
    return true
  }

  private fun showDetails(enterprise: Enterprise) {
    LogHelper.d(TAG, "Enterprise ID: ${enterprise.id}")
    val bundle = Bundle().apply {
      putString(Enterprise.Companion.PARAMS.ENTERPRISE_NAME_PARAM, enterprise.enterpriseName)
      putLong(Enterprise.Companion.PARAMS.ENTERPRISE_ID_PARAM, enterprise.id)
    }
    navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
  }
}