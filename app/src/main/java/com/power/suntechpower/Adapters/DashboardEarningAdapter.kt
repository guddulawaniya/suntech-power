package com.power.suntechpower.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.power.suntechpower.Models.DashboardResponse
import com.power.suntechpower.R
import com.power.suntechpower.databinding.EarningRowItemBinding

class DashboardEarningAdapter(private val list: ArrayList<DashboardResponse>) : RecyclerView.Adapter<DashboardEarningAdapter.ViewHolder>()  {

    inner class ViewHolder(val binding: EarningRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: EarningRowItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.earning_row_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.totalearningimage.setImageResource(list[position].image)
        holder.binding.titletext.text = list[position].title
        holder.binding.amounttext.text = list[position].amount
    }
}