package com.power.suntechpower.Activity

import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityUpdateBankDetailsBinding

class update_bank_details : AppCompatActivity() {
    lateinit var binding : ActivityUpdateBankDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_bank_details)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_bank_details)
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

        binding.backicon.setOnClickListener {
            finish()
        }
        binding.choosefile.setOnClickListener {
            checkCameraPermission()
        }
        binding.ContinueButtom.setOnClickListener {
            finish()
        }
    }
    private val requestFilePermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            openFilePicker() // Open the file picker if permission is granted
        } else {

            Toast.makeText(this, "Permission denied to read files.", Toast.LENGTH_SHORT).show()
        }

    }


    // Launcher to handle file picker result
    private val openFilePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val uri: Uri? = result.data?.data
            uri?.let {
                // Handle the selected file (PDF or DOC)
                Toast.makeText(this, "File selected: $uri", Toast.LENGTH_SHORT).show()
                // Process the file using the URI
            }
        }
    }

    private fun checkCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                openFilePicker()
            } else {
                requestFilePermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        } else {
            openFilePicker()
        }
    }


    // Function to open file picker for PDFs or DOCs
    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "*/*"  // Generic file picker
            addCategory(Intent.CATEGORY_OPENABLE)
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
        }
        openFilePickerLauncher.launch(intent) // Launch file picker
    }
}