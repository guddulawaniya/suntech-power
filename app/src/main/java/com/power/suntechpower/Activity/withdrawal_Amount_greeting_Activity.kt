package com.power.suntechpower.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.power.suntechpower.MainActivity
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityWithdrawalAmountGreetingBinding

class withdrawal_Amount_greeting_Activity : AppCompatActivity() {
    lateinit var binding : ActivityWithdrawalAmountGreetingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdrawal_amount_greeting)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_withdrawal_amount_greeting)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color
            window.statusBarColor = Color.parseColor("#FFFFFFFF")
        }

        // For Android 6.0 (API 23) and above, change the status bar icons to light or dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Set the status bar icons to dark if you have a light background
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        binding.okbutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}