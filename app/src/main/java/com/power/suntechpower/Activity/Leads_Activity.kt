package com.power.suntechpower.Activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.power.suntechpower.Adapters.LeadstabLayoutAdapter
import com.power.suntechpower.Models.ApproveLeadsResponse
import com.power.suntechpower.Models.OpensLeadsResponse
import com.power.suntechpower.Models.ProcessLeadsResponse
import com.power.suntechpower.Models.RejectLeadsResponse
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityOpenLeadsBinding


class Leads_Activity : AppCompatActivity() {

    lateinit var binding: ActivityOpenLeadsBinding

    private var openleadList: ArrayList<OpensLeadsResponse> = ArrayList()
    private var processleadList: ArrayList<ProcessLeadsResponse> = ArrayList()
    private var approveleadList: ArrayList<ApproveLeadsResponse> = ArrayList()
    private var rejectleadList: ArrayList<RejectLeadsResponse> = ArrayList()

    var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_leads)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_open_leads)

        prepareopenleadList()

        index = intent.getIntExtra("index",0)


        // Check if the Android version is Lollipop (API 21) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color
            window.statusBarColor = Color.parseColor("#FFFFFFFF") // Tomato red color
        }

        // For Android 6.0 (API 23) and above, change the status bar icons to light or dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Set the status bar icons to dark if you have a light background
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        binding.backicon.setOnClickListener {
            finish()
        }
        val adapter: FragmentStateAdapter = LeadstabLayoutAdapter(this,
                openleadList,processleadList,approveleadList,rejectleadList)
        binding.viewPager.setAdapter(adapter)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Set tab titles based on position
            tab.text = when (position) {
                0 -> "Open Leads"
                1 -> "Process Leads"
                2 -> "Approved Leads"
                3 -> "Reject Leads"
                else -> "Tab ${position + 1}"
            }
        }.attach()

        binding.tabLayout.getTabAt(index)?.select()
        binding.tabLayout.setSelectedTabIndicatorHeight(0)
        binding.tabLayout.setSelectedTabIndicatorColor(Color.TRANSPARENT)
    }

    private fun prepareopenleadList() {

        openleadList.clear()
        approveleadList.clear()
        processleadList.clear()
        rejectleadList.clear()

        for (i in 0 until 20) {
            openleadList.add(
                OpensLeadsResponse(
                    "$i",
                    "Credit Card",
                    "AU Small Finance Bank",
                    "AU LIT Credit Card",
                    "KARUN Kumar",
                    "KA@GMAIL.COM",
                    "*****B7",
                    "Pending"
                )
            )
            processleadList.add(
                ProcessLeadsResponse(
                    "$i",
                    "Credit Card",
                    "AU Small Finance Bank",
                    "AU LIT Credit Card",
                    "KARUN Kumar",
                    "KA@GMAIL.COM",
                    "*****B7",
                    "Pending"
                )
            )

            approveleadList.add(
                ApproveLeadsResponse(
                    "$i",
                    "Credit Card",
                    "AU Small Finance Bank",
                    "AU LIT Credit Card",
                    "KARUN Kumar",
                    "KA@GMAIL.COM",
                    "*****B7",
                    "Pending"
                )
            )
            rejectleadList.add(
                RejectLeadsResponse(
                    "$i",
                    "Credit Card",
                    "AU Small Finance Bank",
                    "AU LIT Credit Card",
                    "KARUN Kumar",
                    "KA@GMAIL.COM",
                    "*****B7",
                    "Pending"
                )
            )
        }


    }
}