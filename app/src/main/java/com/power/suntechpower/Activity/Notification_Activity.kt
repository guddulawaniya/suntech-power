package com.power.suntechpower.Activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.power.suntechpower.Adapters.NotificationAdapter
import com.power.suntechpower.Models.NotificationResponse
import com.power.suntechpower.Models.OpensLeadsResponse
import com.power.suntechpower.Models.Wallet_HistoryResponse
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityNotificationBinding

class Notification_Activity : AppCompatActivity() {
    private var notificationlist: ArrayList<NotificationResponse> = ArrayList()
    lateinit var binding : ActivityNotificationBinding
    private var notificationAdapter: NotificationAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color
            window.statusBarColor = Color.parseColor("#FFFFFFFF")
        }

        // For Android 6.0 (API 23) and above, change the status bar icons to light or dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Set the status bar icons to dark if you have a light background
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        binding.backicon.setOnClickListener {
            finish()
        }
        prepareopenleadList()
        notificationAdapter = NotificationAdapter(notificationlist,this)
        binding.notificationRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.notificationRecyclerview.adapter = notificationAdapter

    }

    private fun prepareopenleadList() {

        notificationlist.clear()

        for (i in 0 until 20) {
            notificationlist.add(
                NotificationResponse(
                    "New Offers only for you!",
                    "New Exciting Offers only for you! Grab it now",
                    "2",
                    "1 min ago",
                )
            )
        }
    }
}