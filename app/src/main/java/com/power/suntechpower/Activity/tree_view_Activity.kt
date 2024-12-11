package com.power.suntechpower.Activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.power.suntechpower.Adapters.TreeParentAdapter
import com.power.suntechpower.Models.ChildItem
import com.power.suntechpower.Models.TreeIdataResponse

import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityTreeViewBinding

class tree_view_Activity : AppCompatActivity() {
    lateinit var binding: ActivityTreeViewBinding

    private var connectionlist: List<TreeIdataResponse> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tree_view)

        // Set the status bar color for Lollipop and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#FFFFFFFF") // White status bar
        }

        // For Android 6.0 (API 23) and above, change the status bar icons to dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        val childList1 = listOf(ChildItem("123456","amit"), ChildItem("123456","amit"))
        val childList2 = listOf(ChildItem("123456","amit"))

        connectionlist = listOf(
            TreeIdataResponse(childList1),
            TreeIdataResponse(childList2)
        )

        binding.backicon.setOnClickListener { finish() }

        val childAdapter = TreeParentAdapter(connectionlist)
        binding.parentRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.parentRecyclerview.adapter = childAdapter

    }
}
