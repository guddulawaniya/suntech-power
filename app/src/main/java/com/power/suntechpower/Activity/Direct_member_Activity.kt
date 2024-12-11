package com.power.suntechpower.Activity

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.power.suntechpower.Models.DownlineResponse
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityDirectMemberBinding
import java.util.Calendar

class Direct_member_Activity : AppCompatActivity() {
    private var datalist: ArrayList<DownlineResponse> = ArrayList()
    private var filteredList: ArrayList<DownlineResponse> = ArrayList()
    lateinit var binding : ActivityDirectMemberBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_direct_member)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_direct_member)

        // Prepare the data
        prepareOpenLeadList()
        setTableData(datalist)  // Initially set all data

        // Set the status bar color for Lollipop and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#FFFFFFFF") // White status bar
        }

        // For Android 6.0 (API 23) and above, change the status bar icons to dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // Handle back button click
        binding.backicon.setOnClickListener { finish() }

        binding.startopencalender.setOnClickListener {
            openCalendar(true)
        }

        binding.enddatecalender.setOnClickListener {
            openCalendar(false)
        }

        // Search functionality
        binding.searchtext.addTextChangedListener { text ->
            val query = text.toString().trim()
            // Filter the list based on the search query
            filteredList = filterData(query)
            // Update the table with filtered data
            setTableData(filteredList)
        }

    }

    // Updates the TableLayout with the given list
    private fun setTableData(list: ArrayList<DownlineResponse>) {
        // Clear the existing rows before adding new data
        val childCount = binding.tableLayout.childCount
        for (i in childCount - 1 downTo 1) {
            binding.tableLayout.removeViewAt(i)  // Remove dynamic rows starting from the last one
        }

        for (person in list) {
            // Create a new TableRow
            val tableRow = TableRow(this)

            // Add TextViews for each field in the DownlineResponse object
            val idTextView = TextView(this).apply {
                text = person.sno
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val nameTextView = TextView(this).apply {
                text = person.userid
                setPadding(16, 16, 16, 16)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val fullnameTextView = TextView(this).apply {
                text = person.fullname
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val rankTextView = TextView(this).apply {
                text = person.rank
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val levelTextView = TextView(this).apply {
                text = person.level
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val personalSaleTextView = TextView(this).apply {
                text = person.personalsale
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val groupSaleTextView = TextView(this).apply {
                text = person.groupsale
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val registerDateTextView = TextView(this).apply {
                text = person.registerdate
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            // Divider between rows
            val divider = View(this).apply {
                layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1)
                setBackgroundColor(Color.GRAY)
            }

            // Add TextViews to the TableRow
            tableRow.addView(idTextView)
            tableRow.addView(nameTextView)
            tableRow.addView(fullnameTextView)
            tableRow.addView(rankTextView)
            tableRow.addView(levelTextView)
            tableRow.addView(personalSaleTextView)
            tableRow.addView(groupSaleTextView)
            tableRow.addView(registerDateTextView)

            // Add TableRow to TableLayout
            binding.tableLayout.addView(tableRow)
            binding.tableLayout.addView(divider)  // Add the divider after each row
        }
    }

    // Filters the data based on the query
    private fun filterData(query: String): ArrayList<DownlineResponse> {
        return if (query.isEmpty()) {
            datalist // Return original data if search query is empty
        } else {
            // Filter data based on the query, checking all fields of DownlineResponse
            datalist.filter { person ->
                person.sno.contains(query, ignoreCase = true) ||
                        person.userid.contains(query, ignoreCase = true) ||
                        person.fullname.contains(query, ignoreCase = true) ||
                        person.rank.contains(query, ignoreCase = true) ||
                        person.level.contains(query, ignoreCase = true) ||
                        person.personalsale.contains(query, ignoreCase = true) ||
                        person.groupsale.contains(query, ignoreCase = true) ||
                        person.registerdate.contains(query, ignoreCase = true)
            } as ArrayList<DownlineResponse>
        }
    }

    // Prepare the initial data list
    private fun prepareOpenLeadList() {
        datalist.clear()
        for (i in 0 until 20) {
            datalist.add(
                DownlineResponse(
                    "$i", "HA893952", "Gaurav", "%", "1", "0.00", "0", "2024-02-23"
                )
            )
        }
    }

    private fun openCalendar(b: Boolean) {
        // Get the current date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Create DatePickerDialog
        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            // Handle date selection
            // Month is zero-indexed, so add 1 to display correct month
            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            if (b){

                binding.startopencalender.text = selectedDate
            }else
            {
                binding.enddatecalender.text = selectedDate
            }
        }, year, month, day)

        // Show the calendar
        datePickerDialog.show()
    }
}