package com.power.suntechpower.Activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.power.suntechpower.Models.LevelIncomeReportResponse
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityLevelIncomeReportBinding

class Level_income_report_Activity : AppCompatActivity() {
    lateinit var binding : ActivityLevelIncomeReportBinding
    private var datalist: ArrayList<LevelIncomeReportResponse> = ArrayList()
    private var filteredList: ArrayList<LevelIncomeReportResponse> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_income_report)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_level_income_report)

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
    private fun setTableData(list: ArrayList<LevelIncomeReportResponse>) {
        // Clear the existing rows before adding new data
        val childCount = binding.tableLayout.childCount
        for (i in childCount - 1 downTo 1) {
            binding.tableLayout.removeViewAt(i)  // Remove dynamic rows starting from the last one
        }

        for (person in list) {
            // Create a new TableRow
            val tableRow = TableRow(this)

            // Add TextViews for each field in the DownlineResponse object
            val snoTextView = TextView(this).apply {
                text = person.sno
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val dateTextView = TextView(this).apply {
                text = person.date
                setPadding(16, 16, 16, 16)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val useridTextView = TextView(this).apply {
                text = person.userid
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
            val customernameTextView = TextView(this).apply {
                text = person.customerpname
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val customermobileTextView = TextView(this).apply {
                text = person.customerpmobile
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val customerpanTextView = TextView(this).apply {
                text = person.customerpan
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val cvTextView = TextView(this).apply {
                text = person.cv
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val commissionTextView = TextView(this).apply {
                text = person.commission
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val commission_amountTextView = TextView(this).apply {
                text = person.commission_amount
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }
            val tdsTextView = TextView(this).apply {
                text = person.tds
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }
            val final_amountTextView = TextView(this).apply {
                text = person.final_amount
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }
            val commission_StatusTextView = TextView(this).apply {
                text = person.commission_status
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
            tableRow.addView(snoTextView)
            tableRow.addView(dateTextView)
            tableRow.addView(useridTextView)
            tableRow.addView(levelTextView)
            tableRow.addView(customernameTextView)
            tableRow.addView(customermobileTextView)
            tableRow.addView(customerpanTextView)
            tableRow.addView(cvTextView)
            tableRow.addView(commissionTextView)
            tableRow.addView(commission_amountTextView)
            tableRow.addView(tdsTextView)
            tableRow.addView(final_amountTextView)
            tableRow.addView(commission_StatusTextView)

            // Add TableRow to TableLayout
            binding.tableLayout.addView(tableRow)
            binding.tableLayout.addView(divider)  // Add the divider after each row
        }
    }

    // Filters the data based on the query
    private fun filterData(query: String): ArrayList<LevelIncomeReportResponse> {
        return if (query.isEmpty()) {
            datalist // Return original data if search query is empty
        } else {
            // Filter data based on the query, checking all fields of DownlineResponse
            datalist.filter { person ->
                person.sno.contains(query, ignoreCase = true) ||
                        person.customerpname.contains(query, ignoreCase = true) ||
                        person.customerpmobile.contains(query, ignoreCase = true) ||
                        person.customerpan.contains(query, ignoreCase = true)
            } as ArrayList<LevelIncomeReportResponse>
        }
    }

    // Prepare the initial data list
    private fun prepareOpenLeadList() {
        datalist.clear()
        for (i in 0 until 20) {
            datalist.add(
                LevelIncomeReportResponse(
                    "$i",
                    "02 dec 2024",
                    "25",
                    "1",
                    "Gaurav",
                    "9638527414",
                    "abcfv5689hg",
                    "50",
                    "500",
                    "5000",
                    "20",
                    "2000",
                    "Pending"
                )
            )
        }
    }
}