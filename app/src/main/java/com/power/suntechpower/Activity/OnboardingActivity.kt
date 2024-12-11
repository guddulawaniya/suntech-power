package com.power.suntechpower.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.power.suntechpower.Adapters.OnboardingPagerAdapter
import com.power.suntechpower.MainActivity
import com.power.suntechpower.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var onboardingPagerAdapter: OnboardingPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the ViewPager2 adapter
        onboardingPagerAdapter = OnboardingPagerAdapter(this)
        binding.viewPager.adapter = onboardingPagerAdapter

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

        // Optionally, add a page change listener for logic
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Optional: Handle page changes if needed
            }
        })
    }

    // Move to the next page or finish onboarding when the user reaches the last page
    fun moveToNextPage() {
        val currentPage = binding.viewPager.currentItem
        if (currentPage < onboardingPagerAdapter.itemCount - 1) {
            binding.viewPager.currentItem = currentPage + 1
        } else {
            // Onboarding complete, navigate to MainActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
