package com.power.suntechpower.Activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.power.suntechpower.Adapters.DashboardEarningAdapter
import com.power.suntechpower.Adapters.MenuListAdapter
import com.power.suntechpower.Models.DashboardMenuModel
import com.power.suntechpower.Models.DashboardResponse
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityDashboardBinding

class Dashboard_Activity : AppCompatActivity() {
    lateinit var binding : ActivityDashboardBinding
    private var menuListAdapter: DashboardEarningAdapter? = null
    private var datalist: ArrayList<DashboardResponse> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)

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

        prepareDataList()

        val spanCount = 2

        val spacing = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._16sdp)

        val layoutManager = GridLayoutManager(this, spanCount)
        binding.earningrecylerview.layoutManager = layoutManager

        binding.earningrecylerview.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, true))

    }

    private fun prepareDataList() {
        // Now it's safe to call clear() and add items to the menuList
        datalist.clear()
        datalist.add(DashboardResponse(R.drawable.totalearning,"Total Earning ","₹ 1,080"))
        datalist.add(DashboardResponse(R.drawable.selfearnimage,"Self Earning","₹ 0.00"))
        datalist.add(DashboardResponse(R.drawable.levelearningimage,"Level Earning","₹ 1,080"))
        datalist.add(DashboardResponse(R.drawable.teamearningimage,"Team Earning","₹ 11,080"))
        datalist.add(DashboardResponse(R.drawable.personalimage,"Personal Sale ","₹0.00"))
        datalist.add(DashboardResponse(R.drawable.groupimage,"Group Sale","₹ 0.80"))
        datalist.add(DashboardResponse(R.drawable.totaldownline,"TOTAL DOWNLINE","₹ 1080"))
        datalist.add(DashboardResponse(R.drawable.totaldirectimage,"TOTAL DIRECT","₹ 1380"))
        menuListAdapter = DashboardEarningAdapter(datalist)
        binding.earningrecylerview.adapter = menuListAdapter
    }
}