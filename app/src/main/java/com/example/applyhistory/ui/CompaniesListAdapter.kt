package com.example.applyhistory.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.applyhistory.R
import com.example.applyhistory.databinding.CompaniesListItemViewBinding
import com.example.applyhistory.db.Company


class CompaniesListAdapter : ListAdapter<Company, CompaniesListAdapter.ItemHolder>(DIFF_CALLBACK),Filterable {
  private var listener: OnItemClickListener? = null
  private var originalList: List<Company>? = null
  private var filteredList: List<Company>? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
    val binding: CompaniesListItemViewBinding = DataBindingUtil.inflate(
      LayoutInflater.from(parent.context),
      R.layout.companies_list_item_view,
      parent,
      false
    )

    return ItemHolder(binding)
  }

  override fun submitList(list: List<Company>?) {
    super.submitList(if (list != null) ArrayList(list) else null)
  }

  fun setList(list: List<Company>?) {
    originalList = list
    submitList(list)
  }

  override fun onBindViewHolder(holder: ItemHolder, position: Int) {
    holder.binding.item = getItem(position)
  }

  fun setOnItemClickListener(listener: OnItemClickListener) {
    this.listener = listener
  }

  interface OnItemClickListener {
    fun onItemClick(item: Company, position: Int)
  }

  inner class ItemHolder(val binding: CompaniesListItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
      itemView.setOnClickListener {
        val position = bindingAdapterPosition
        if (listener != null && position != RecyclerView.NO_POSITION) {
          val item = getItem(position)
          listener!!.onItemClick(item, position)
        }
      }
    }
  }

  companion object {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Company>() {
      override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.companyName == newItem.companyName
                && oldItem.lastUpdateDate == newItem.lastUpdateDate
                && oldItem.applyStatus == newItem.applyStatus
      }
    }
  }

  override fun getFilter(): Filter {
    return object : Filter() {
      override fun performFiltering(charSequence: CharSequence): FilterResults {
        val charString = charSequence.toString().lowercase().trim { it <= ' ' }
        filteredList = if (charString.isEmpty()) {
          originalList
        } else {
          val filtered = ArrayList<Company>()
          for (row in originalList as List<Company>) {
            if (row.companyName.contains(charSequence)
              || row.description.contains(charSequence)
              || row.lastUpdateDate.contains(charSequence)
              || row.companyWebSite.contains(charSequence)
            ) {
              filtered.add(row)
            }
          }
          filtered
        }
        val filterResults = FilterResults()
        filterResults.values = filteredList
        return filterResults
      }

      override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
        if (filterResults.values is List<*>) {
          val result: List<Company> = (filterResults.values as List<*>).filterIsInstance<Company>()
          submitList(result)
        }
      }
    }
  }
}
