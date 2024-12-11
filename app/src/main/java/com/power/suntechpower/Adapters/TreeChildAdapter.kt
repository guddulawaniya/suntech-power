package com.power.suntechpower.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.power.suntechpower.Models.ChildItem
import com.power.suntechpower.R
import com.power.suntechpower.databinding.TreeGraphRowItemBinding



class TreeChildAdapter(private val list: List<ChildItem>,private val flag: Boolean, ) : RecyclerView.Adapter<TreeChildAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: TreeGraphRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: TreeGraphRowItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.tree_graph_row_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.nametext.text = list[position].name
        if (flag){
            holder.binding.uperline.visibility = View.GONE
        }else{
            holder.binding.uperline.visibility = View.VISIBLE
        }

    }
}
