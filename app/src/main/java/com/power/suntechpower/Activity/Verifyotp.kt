package com.power.suntechpower.Activity

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.power.suntechpower.MainActivity
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityLoginBinding
import com.power.suntechpower.databinding.ActivityVerifyotpBinding

class Verifyotp : AppCompatActivity() {
    lateinit var binding : ActivityVerifyotpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifyotp)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verifyotp)
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

        binding.verifybutton.setOnClickListener {
            showCustomDialog()
        }
    }


    private fun showCustomDialog() {

        val alertDialog = MaterialAlertDialogBuilder(this, R.style.MyRounded_MaterialComponents_MaterialAlertDialog)
            .setView(R.layout.verifyotp_custom_dialogbox)  // Set the custom layout
            .show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



             val closeButton = alertDialog.findViewById<Button>(R.id.okbutton)


        closeButton?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }
    }

}