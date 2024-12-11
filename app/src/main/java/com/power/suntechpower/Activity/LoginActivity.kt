package com.power.suntechpower.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityLoginBinding
import com.power.suntechpower.databinding.ActivityOnboardingBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    var toggalpassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
         binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.sendcodebutton.setOnClickListener {
            startActivity(Intent(this,Verifyotp::class.java))
        }
        binding.forgetpassword.setOnClickListener {
            startActivity(Intent(this,forget_password_activity::class.java))
        }

        binding.signup.setOnClickListener {
            startActivity(Intent(this,Signup_Activity::class.java))
        }


        binding.passwordToggle.setOnClickListener {
            if (!toggalpassword){
                binding.passwordToggle.setImageResource(R.drawable.visibility_off_24px)
                toggalpassword = true
                binding.passwordtext.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }else
            {
                binding.passwordToggle.setImageResource(R.drawable.visibility_24px)
                toggalpassword = false
                binding.passwordtext.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD

            }

        }

    }
}