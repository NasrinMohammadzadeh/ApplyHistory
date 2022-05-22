package com.example.applyhistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.applyhistory.databinding.CompaniesListItemViewBinding


class CompaniesListAdapter : ListAdapter<Company, CompaniesListAdapter.ItemHolder>(DIFF_CALLBACK) {
  private var listener: OnItemClickListener? = null

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
        return oldItem == newItem
      }

      override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.id == newItem.id
      }
    }
  }
}
