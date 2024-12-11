package com.power.suntechpower.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityForgetOtpVerificationBinding

class forget_otp_verification : AppCompatActivity() {
    lateinit var binding: ActivityForgetOtpVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_otp_verification)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_otp_verification)

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

        val text = "We have sent you a OTP to verify your phone number (+91) 9658456xxxx"
        val spannableString = SpannableString(text)


        val phoneNumberStartIndex = text.indexOf("9658456xxxx") // Find the start index of the phone number
        val phoneNumberEndIndex = phoneNumberStartIndex + "9658456xxxx".length // Find the end index

        val customColor = Color.parseColor("#163300")
        spannableString.setSpan(ForegroundColorSpan(customColor), phoneNumberStartIndex, phoneNumberEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.text.text = spannableString

        binding.backicon.setOnClickListener {
            finish()
        }

        binding.changepassword.setOnClickListener {
            startActivity(Intent(this,Change_passsword_activity::class.java))
        }
    }
}