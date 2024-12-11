package com.power.suntechpower.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.power.suntechpower.MainActivity
import com.power.suntechpower.Models.DashboardMenuModel
import com.power.suntechpower.R
import com.power.suntechpower.databinding.RowNewItemBinding

class MenuListAdapter(
    private val dataSource: ArrayList<DashboardMenuModel>,
    private val outerItemClickListener: OnOuterItemClickListener,
    private val itemClickListener: InnerMenuAdapter.OnItemClickListener) :
    RecyclerView.Adapter<MenuListAdapter.ViewHolder>() {


    interface OnOuterItemClickListener {
        fun onOuterItemClick(name: String)
    }
    // ViewHolder holds RowNewItemBinding directly
    inner class ViewHolder(val binding: RowNewItemBinding) : RecyclerView.ViewHolder(binding.root){


        fun bindInnerRecyclerView(innerList: List<String>) {
            val innerAdapter = InnerMenuAdapter(innerList , itemClickListener)
            binding.innerRecyclerview.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
            binding.innerRecyclerview.adapter = innerAdapter
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the layout and bind it directly to RowNewItemBinding
        val binding: RowNewItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_new_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind the data to the view using the binding object
        holder.binding.textitem.text = dataSource[position].title
        holder.binding.leftimage.setImageResource(dataSource[position].image)


        holder.itemView.setOnClickListener {
            if (dataSource[position].innerMenulist.isEmpty())
            {
                outerItemClickListener.onOuterItemClick(dataSource[position].title)
            }else
            {
                if (holder.binding.innerRecyclerview.visibility == View.VISIBLE){
                    holder.binding.innerRecyclerview.visibility = View.GONE
                    holder.binding.rightimage.setImageResource(R.drawable.right_arrow_icon)
                }else
                {
                    holder.binding.innerRecyclerview.visibility = View.VISIBLE
                    holder.binding.rightimage.setImageResource(R.drawable.arrow_down_icon)
                }
            }

        }
        if(dataSource[position].innerMenulist.isEmpty())
        {
            holder.binding.rightimage.visibility = View.GONE
        }
        val subItems = dataSource[position].innerMenulist // Assume `subItems` is a List<String> in DashboardMenuModel
        holder.bindInnerRecyclerView(subItems)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

}
