package com.power.suntechpower.Activity

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.power.suntechpower.MainActivity
import com.power.suntechpower.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color
            window.statusBarColor = Color.parseColor("#FFFFFFFF")
        }

        // For Android 6.0 (API 23) and above, change the status bar icons to light or dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Set the status bar icons to dark if you have a light background
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        // Find the ImageView
        val splashImage = findViewById<ImageView>(R.id.splashImage)

        // Create the zoom-in animation using ObjectAnimator
        val scaleX = ObjectAnimator.ofFloat(splashImage, "scaleX", 1f, 1.5f)  // Zoom in on X axis
        val scaleY = ObjectAnimator.ofFloat(splashImage, "scaleY", 1f, 1.5f)  // Zoom in on Y axis

        // Set the duration for both scale animations
        scaleX.duration = 1000  // 1 second
        scaleY.duration = 1000  // 1 second

        // Start the zoom-in animations
        scaleX.start()
        scaleY.start()

        // Add a delay to show splash screen for 2 seconds after animation
        Handler().postDelayed({
            // Navigate to the MainActivity after the splash screen is shown
            startActivity(Intent(this, OnboardingActivity::class.java))

            // Close the splash screen activity so it's removed from the back stack
            finish()
        }, 2000)  // 2-second delay (adjust as needed)
    }
}