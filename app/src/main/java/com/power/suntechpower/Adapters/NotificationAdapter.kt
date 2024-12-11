package com.power.suntechpower.Adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.power.suntechpower.Models.NotificationResponse
import com.power.suntechpower.R
import com.power.suntechpower.databinding.NotificationRowLayoutBinding

class NotificationAdapter(private val list: List<NotificationResponse>, private val context: Context) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: NotificationRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: NotificationRowLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.notification_row_layout,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position==0)
        {
            holder.binding.backgroundcard.visibility = View.VISIBLE
            holder.binding.linearLayoutclick.visibility = View.GONE
        }
        else
        {
            holder.binding.linearLayoutclick.visibility = View.VISIBLE
            holder.binding.backgroundcard.visibility = View.GONE
        }


        // Set text for views
        holder.binding.titletext.text = list[position].title
        holder.binding.descriptiontext.text = list[position].desciption // Use description instead of title for description
        holder.binding.time.text = list[position].time
        holder.binding.title1.text = list[position].title
        holder.binding.desciption1.text = list[position].desciption // Use description instead of title for description
        holder.binding.time1.text = list[position].time
        holder.binding.count.text = list[position].count
    }
}
