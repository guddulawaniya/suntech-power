package com.power.suntechpower.Activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.power.suntechpower.Models.ApproveLeadsResponse
import com.power.suntechpower.Models.OpensLeadsResponse
import com.power.suntechpower.Models.ProcessLeadsResponse
import com.power.suntechpower.Models.PropectListResponse
import com.power.suntechpower.Models.RejectLeadsResponse
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityProspectBinding

class Prospect_Activity : AppCompatActivity() {

    lateinit var binding : ActivityProspectBinding
    private var propectList: ArrayList<PropectListResponse> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prospect)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_prospect)


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

        binding.uploadlinear.setOnClickListener {
            checkCameraPermission()
        }
        binding.addbutton.setOnClickListener {
           startActivity(Intent(this,Add_Prospect_Activity::class.java))
        }
        prepareopenleadList()
        setTableData()
    }
    private val requestFilePermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            openFilePicker() // Open the file picker if permission is granted
        } else {

            Toast.makeText(this, "Permission denied to read files.", Toast.LENGTH_SHORT).show()
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
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("application/csv"))
        }
        openFilePickerLauncher.launch(intent) // Launch file picker
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
    private fun setTableData(){
        for (person in propectList) {
            // Create a new TableRow
            val tableRow = TableRow(this)

            // Create TextViews for each field in the row
            val idTextView = TextView(this).apply {
                text = person.sno
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f  // Increased text size
            }

            val nameTextView = TextView(this).apply {
                text = person.Prospect_ID
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val ageTextView = TextView(this).apply {
                text = person.Full_Name
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val addressTextView = TextView(this).apply {
                text = person.Email
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val emailTextView = TextView(this).apply {
                text = person.Contact
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val phoneTextView = TextView(this).apply {
                text = person.State
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val statusTextView = TextView(this).apply {
                text = person.City
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val countryTextView = TextView(this).apply {
                text = person.Address
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }
            val LastActivityTextView = TextView(this).apply {
                text = person.Last_Activity
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }
            val Viewbutton = Button(this).apply {
                text = "View"
                setTextColor(Color.WHITE)
                setPadding(25, 50, 25, 50) // Padding (left, top, right, bottom)
                gravity = Gravity.CENTER // Center text inside the button
                textSize = 14f // Text size of the button
                // Set the background using the context's getDrawable() method (for drawable resources)
                background = ContextCompat.getDrawable(context, R.drawable.button_background)

            }


            val divider = View(this)
            divider.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                1 // Set height of divider
            )
            divider.setBackgroundColor(Color.GRAY) // Divider color
            binding.tableLayout.addView(divider)

            // Add TextViews to the TableRow
            tableRow.addView(idTextView)
            tableRow.addView(nameTextView)
            tableRow.addView(ageTextView)
            tableRow.addView(addressTextView)
            tableRow.addView(emailTextView)
            tableRow.addView(phoneTextView)
            tableRow.addView(statusTextView)
            tableRow.addView(countryTextView)
            tableRow.addView(LastActivityTextView)
            tableRow.addView(Viewbutton)

            // Add TableRow to TableLayout
            binding.tableLayout.addView(tableRow)
        }
    }

    private fun prepareopenleadList() {

        propectList.clear()

        for (i in 0 until 20) {
            propectList.add(
                PropectListResponse(
                    "$i",
                    "Credit Card",
                    "AU Small Finance Bank",
                    "AU LIT Credit Card",
                    "KARUN Kumar",
                    "KA@GMAIL.COM",
                    "*****B7",
                    "Pending",
                    "30 nov 2024"
                )
            )

        }

    }
}