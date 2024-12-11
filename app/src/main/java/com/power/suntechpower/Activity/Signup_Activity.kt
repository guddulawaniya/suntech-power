package com.power.suntechpower.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.power.suntechpower.MainActivity
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivitySignupBinding


class Signup_Activity : AppCompatActivity() {

    lateinit var binding : ActivitySignupBinding
    var checkstatus = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_signup)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        val text = "By creating an account you agree to our Terms and Conditions"
        val spannableString = SpannableString(text)

        spannableString.setSpan(ForegroundColorSpan(Color.BLACK), 40, 60, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) // "agree"

        binding.checktext.setText(spannableString)
        binding.signin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        binding.signup.setOnClickListener {
            startActivity(Intent(this,Verifyotp::class.java))

        }
        binding.checktext.setOnClickListener {
           val intent = Intent(this,Privacy_policy_Activity::class.java)
            intent.putExtra("check",true)
            startActivity(intent)

        }

        binding.checkBox.setOnClickListener {
            if (checkstatus){
                checkstatus = false
                binding.checkBox.setBackgroundResource(R.drawable.checkbox_checked)


            }
            else
            {
                binding.checkBox.setBackgroundResource(R.drawable.checkbox_unchecked)
                checkstatus = true
            }
        }
    }
}