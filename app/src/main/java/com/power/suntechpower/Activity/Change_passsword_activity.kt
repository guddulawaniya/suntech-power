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
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityChangePassswordBinding

class Change_passsword_activity : AppCompatActivity() {
    lateinit var binding : ActivityChangePassswordBinding

    var toggalpassword = false
    var toggalpassword1 = false
    var toggalpassword2 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_passsword)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_passsword)

        val check = intent.getBooleanExtra("dashboard",false)

        if (check){
            binding.backicon.setText("Change Password")
            binding.oldpasswordtext.visibility = View.VISIBLE
            binding.oldpasswordtextedit.visibility = View.VISIBLE
            binding.oldpasswordtextlLinearayout.visibility = View.VISIBLE
        }
        else
        {
            binding.backicon.setText("New Password")
            binding.oldpasswordtext.visibility = View.GONE
            binding.oldpasswordtextedit.visibility = View.GONE
            binding.oldpasswordtextlLinearayout.visibility = View.GONE
        }
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
        binding.confirmButton.setOnClickListener {
            startActivity(Intent(this,Change_password_view::class.java))
        }
        binding.backicon.setOnClickListener {
            finish()
        }

        binding.passwordToggle.setOnClickListener {
            if (!toggalpassword){
                binding.passwordToggle.setImageResource(R.drawable.visibility_off_24px)
                toggalpassword = true
                binding.oldpasswordtextedit.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }else
            {
                binding.passwordToggle.setImageResource(R.drawable.visibility_24px)
                toggalpassword = false
                binding.oldpasswordtextedit.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD

            }

        }
        binding.passwordToggle1.setOnClickListener {
            if (!toggalpassword1){
                binding.passwordToggle1.setImageResource(R.drawable.visibility_off_24px)
                toggalpassword1 = true
                binding.newpasswordtextedit.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }else
            {
                binding.passwordToggle1.setImageResource(R.drawable.visibility_24px)
                toggalpassword1 = false
                binding.newpasswordtextedit.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD

            }

        }

        binding.passwordToggle2.setOnClickListener {
            if (!toggalpassword2){
                binding.passwordToggle2.setImageResource(R.drawable.visibility_off_24px)
                toggalpassword2 = true
                binding.confirmpasswordtextedit.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }else
            {
                binding.passwordToggle2.setImageResource(R.drawable.visibility_24px)
                toggalpassword2 = false
                binding.confirmpasswordtextedit.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD

            }

        }

    }
}