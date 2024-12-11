package com.power.suntechpower.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.power.suntechpower.Models.TreeIdataResponse
import com.power.suntechpower.R

import com.power.suntechpower.databinding.TreeRootRowItemBinding


class TreeParentAdapter(private val list: List<TreeIdataResponse> ) : RecyclerView.Adapter<TreeParentAdapter.ViewHolder>() {
    private var flag = false
    inner class ViewHolder(val binding: TreeRootRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: TreeRootRowItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.tree_root_row_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (list.size==1)
        {
            flag = true
            holder.binding.mediamline.visibility = View.GONE
            holder.binding.verticaline.visibility = View.GONE
        }else
        {
            holder.binding.mediamline.visibility = View.VISIBLE
            holder.binding.verticaline.visibility = View.VISIBLE
        }

        val childAdapter = TreeChildAdapter(list[position].childItems,flag)
        holder.binding.parentRecyclerview.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        holder.binding.parentRecyclerview.adapter = childAdapter

    }
}
