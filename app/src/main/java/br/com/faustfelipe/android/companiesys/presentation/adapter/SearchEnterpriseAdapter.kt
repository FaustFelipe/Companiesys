package br.com.faustfelipe.android.companiesys.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.faustfelipe.android.companiesys.R
import br.com.faustfelipe.android.companiesys.presentation.adapter.SearchEnterpriseAdapter.SearchEnterpriseViewHolder
import br.com.faustfelipe.android.companiesys.presentation.extensions.inflate
import br.com.faustfelipe.android.companiesys.presentation.extensions.loadImage
import br.com.faustfelipe.android.domain.models.Enterprise

class SearchEnterpriseAdapter(
  private val listener: (Enterprise) -> Unit
) : ListAdapter<Enterprise, SearchEnterpriseViewHolder>(
  getSearchEnterpriseDiffUtilCallback()
) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchEnterpriseViewHolder {
    val view = parent.inflate(R.layout.ly_search_enterprise_item)
    return SearchEnterpriseViewHolder(view)
  }

  fun setList(list: List<Enterprise>) {
    this.submitList(list.toMutableList())
  }

  override fun submitList(list: MutableList<Enterprise>?) {
    super.submitList(list?.let { ArrayList(it) })
  }

  override fun onBindViewHolder(holder: SearchEnterpriseViewHolder, position: Int) {
    val item = getItem(position)
    holder.bind(item, listener)
  }

  class SearchEnterpriseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val container: ConstraintLayout = itemView.findViewById(R.id.container)
    private val imgEnterprise: AppCompatImageView = itemView.findViewById(R.id.img_enterprise)
    private val txtEnterpriseName: AppCompatTextView =
      itemView.findViewById(R.id.txt_enterprise_name)
    private val txtEnterpriseType: AppCompatTextView =
      itemView.findViewById(R.id.txt_enterprise_type)
    private val txtEnterpriseCountry: AppCompatTextView =
      itemView.findViewById(R.id.txt_enterprise_country)

    fun bind(enterprise: Enterprise, listener: (Enterprise) -> Unit) {
      container.setOnClickListener {
        listener.invoke(enterprise)
      }
      imgEnterprise.loadImage(enterprise.photo)
      txtEnterpriseName.text = enterprise.enterpriseName
      txtEnterpriseType.text = enterprise.enterpriseType
      txtEnterpriseCountry.text = enterprise.country
    }
  }
}

internal fun getSearchEnterpriseDiffUtilCallback(): DiffUtil.ItemCallback<Enterprise> {
  return object : DiffUtil.ItemCallback<Enterprise>() {
    override fun areItemsTheSame(oldItem: Enterprise, newItem: Enterprise): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Enterprise, newItem: Enterprise): Boolean {
      return oldItem == newItem
    }
  }
}
