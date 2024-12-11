package com.power.suntechpower.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.power.suntechpower.Activity.Official_Announcement_Activity
import com.power.suntechpower.Models.AnnoucementResponse
import com.power.suntechpower.R
import com.power.suntechpower.databinding.AnnouncementRowItemBinding

class AnnoucementAdapter(private val list: List<AnnoucementResponse>, private val context: Context) : RecyclerView.Adapter<AnnoucementAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AnnouncementRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: AnnouncementRowItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.announcement_row_item,
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
            holder.binding.leftimage.setImageResource(R.drawable.announcementimage)
        }
        else
        {
            holder.binding.leftimage.setImageResource(R.drawable.inactive_annoucement)
            holder.binding.linearLayoutclick.visibility = View.VISIBLE
            holder.binding.backgroundcard.visibility = View.GONE
        }

        holder.binding.viewbutton.setOnClickListener {
            context.startActivity(Intent(context,Official_Announcement_Activity::class.java))
        }


        // Set text for views
        holder.binding.titletext.text = list[position].title
        holder.binding.descriptiontext.text = list[position].title
        holder.binding.time.text = list[position].title

        holder.binding.title1.text = list[position].title
        holder.binding.desciption1.text = list[position].title
        holder.binding.time1.text = list[position].title
    }
}
