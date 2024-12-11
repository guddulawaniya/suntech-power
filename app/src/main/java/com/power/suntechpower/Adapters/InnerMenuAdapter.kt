package com.power.suntechpower.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.power.suntechpower.MainActivity
import com.power.suntechpower.R
import com.power.suntechpower.databinding.SideMenuInnerRowItemBinding

class InnerMenuAdapter(private val subItems: List<String>, private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<InnerMenuAdapter.InnerViewHolder>() {

    inner class InnerViewHolder(val binding: SideMenuInnerRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val binding: SideMenuInnerRowItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.side_menu_inner_row_item,
            parent,
            false
        )
        return InnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.binding.textitem.text = subItems[position]
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(subItems[position])
        }
    }

    override fun getItemCount(): Int {
        return subItems.size
    }
    interface OnItemClickListener {
        fun onItemClick(
            name: String
        )
    }
}

