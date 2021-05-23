package br.com.faustfelipe.android.companiesys.presentation.details

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import br.com.faustfelipe.android.companiesys.R
import br.com.faustfelipe.android.companiesys.common.BaseFragment
import br.com.faustfelipe.android.companiesys.presentation.extensions.loadImage
import br.com.faustfelipe.android.companiesys.presentation.extensions.setVisibilityGoneForBoolean
import br.com.faustfelipe.android.domain.models.Enterprise
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment(R.layout.fragment_details) {

  private var enterpriseName: String? = null
  private var enterpriseId: Long? = null

  private val viewModel: DetailsViewModel by viewModel()

  private val imgEnterprise: AppCompatImageView get() = img_enterprise
  private val txtEnterpriseDescription: AppCompatTextView get() = txt_enterprise_description
  private val progressBar: ProgressBar get() = progress_bar

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    arguments?.let {
      enterpriseId = it.getLong(Enterprise.Companion.PARAMS.ENTERPRISE_ID_PARAM)
      enterpriseName = it.getString(Enterprise.Companion.PARAMS.ENTERPRISE_NAME_PARAM)
    }

    setupView()
    setupActions()
    setupObservables()
  }

  override fun setupView() {
    toolbarView = toolbar
    setupToolbarWithBackOption(title = enterpriseName ?: "")
  }

  override fun setupActions() {
  }

  override fun setupObservables() {
    viewModel.apply {
      showEnterprise(enterpriseId ?: 0L)
      loading.observe(viewLifecycleOwner, {
        progressBar.setVisibilityGoneForBoolean(it)
        imgEnterprise.setVisibilityGoneForBoolean(!it)
        txtEnterpriseDescription.setVisibilityGoneForBoolean(!it)
      })

      errorMessage.observe(viewLifecycleOwner, {
        Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
      })

      relogin.observe(viewLifecycleOwner, {
        if (it) navigate(R.id.action_detailsFragment_to_signInFragment)
      })

      enterprise.observe(viewLifecycleOwner, {
        imgEnterprise.loadImage(it.photo)
        txtEnterpriseDescription.text = it.description
      })
    }
  }
}