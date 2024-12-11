package com.power.suntechpower.Activity

import android.graphics.Color
import android.os.Build
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityEditProfileBinding

class Edit_Profile_Activity : AppCompatActivity() {
    lateinit var binding : ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)

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

        binding.camerapickup.setOnClickListener {  showImagePickerDialog()}

        binding.backicon.setOnClickListener {
            finish()
        }
        binding.updateprofilebutton.setOnClickListener {
            finish()
        }
    }

    private fun showImagePickerDialog() {
        val options = arrayOf("Camera", "Gallery","Cancle")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pick an image source")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> checkCameraPermission()
                    1 -> checkGalleryPermission()
                    2-> dialog.dismiss()
                }
            }
        builder.create().show()
    }
    private fun checkCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        } else {
            openCamera()
        }
    }

    private fun checkGalleryPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                requestGalleryPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
            }
        } else {
            openGallery()
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        openCameraLauncher.launch(intent)
    }


    private fun openGallery() {
        openGalleryLauncher.launch("image/*")
    }

    private val requestCameraPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(this, "Camera permission is required", Toast.LENGTH_SHORT).show()
        }
    }

    private val requestGalleryPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            openGallery()
        } else {
            Toast.makeText(this, "Storage permission is required", Toast.LENGTH_SHORT).show()
        }
    }

    private val openGalleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            // Handle the image URI
            binding.profileImage.setImageURI(uri)
//            Toast.makeText(this, "Image selected: $uri", Toast.LENGTH_SHORT).show()
        }
    }

    private val openCameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            // Handle the image taken from the camera
            val imageUri = result.data?.data

            if (imageUri != null) {
                binding.profileImage.setImageURI(imageUri)
            } else {
                val bitmap = result.data?.extras?.get("data") as? Bitmap
                binding.profileImage.setImageBitmap(bitmap)
            }

//            binding.profileImage.setImageURI(imageUri)
//            Toast.makeText(this, "Image captured: $imageUri", Toast.LENGTH_SHORT).show()
        }
    }

}