package com.power.suntechpower.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.power.suntechpower.Activity.Official_Announcement_Activity
import com.power.suntechpower.Activity.OpenTicket_Activity
import com.power.suntechpower.Activity.Ticket_Response_Activity
import com.power.suntechpower.Models.AnnoucementResponse
import com.power.suntechpower.R
import com.power.suntechpower.databinding.AnnouncementRowItemBinding
import com.power.suntechpower.databinding.HelpRowItemBinding

class HelpAdapter(private val list: List<String>, private val context: Context) : RecyclerView.Adapter<HelpAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: HelpRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: HelpRowItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.help_row_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.title.text = list[position]

        if (position==0){
            holder.itemView.setOnClickListener {
                context.startActivity(Intent(context,OpenTicket_Activity::class.java))
            }
        }
        else{
            holder.itemView.setOnClickListener {
                context.startActivity(Intent(context,Ticket_Response_Activity::class.java))
            }
        }




    }
}
