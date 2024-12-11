package com.power.suntechpower.Activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
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
import com.power.suntechpower.BaseActivity
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityShareBinding

class Share_Activity : BaseActivity() {
    lateinit var binding : ActivityShareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_share)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color
            window.statusBarColor = Color.parseColor("#FFFFFFFF") // Tomato red color
        }

        // For Android 6.0 (API 23) and above, change the status bar icons to light or dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Set the status bar icons to dark if you have a light background
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        binding.backicon.setOnClickListener { finish() }

        binding.copybutton.setOnClickListener {
            copyTextToClipboard()
        }

        binding.shareFacebookButton.setOnClickListener { shareToFacebook("com.facebook.katana") }
        binding.shareInstagramButton.setOnClickListener { shareToFacebook("com.instagram.android") }
        binding.shareWhatsappButton.setOnClickListener { shareToFacebook("com.whatsapp") }
        binding.shareTwitterButton.setOnClickListener { shareToFacebook("com.twitter.android") }
        binding.shareLinkdinButton.setOnClickListener { shareToFacebook("com.linkedin.android") }

    }

    private fun shareToFacebook(packageName: String) {
        val textToShare = binding.textlink.text.toString()
        if (isAppInstalled(packageName)) {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, textToShare)
                type = "text/plain"
                setPackage(packageName) // Facebook package
            }
            startActivity(shareIntent)
        } else {
            toast(this,"Application not installed")
        }
    }

    private fun isAppInstalled(packageName: String): Boolean {
        return try {
            packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: Exception) {
            false
        }
    }


    private fun shareText() {
        val textToShare = binding.textlink.text.toString()
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, textToShare)
            type = "text/plain"
        }

        // Filter to allow sharing only to specific apps
        val resolvedActivities = packageManager.queryIntentActivities(shareIntent, 0)
        val filteredActivities = resolvedActivities.filter { activity ->
            val packageName = activity.activityInfo.packageName.lowercase()
            packageName.contains("whatsapp") || packageName.contains("facebook") ||
                    packageName.contains("instagram") || packageName.contains("twitter")
        }

        if (filteredActivities.isNotEmpty()) {
            // Show the chooser dialog with the filtered apps only
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        } else {
            toast(this,"No supported apps found to share")

        }
    }

    private fun copyTextToClipboard() {
        val textToCopy = binding.textlink.text.toString()
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", textToCopy)
        clipboard.setPrimaryClip(clip)
    }
}