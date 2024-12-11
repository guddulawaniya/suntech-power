package com.power.suntechpower.Activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.power.suntechpower.Adapters.MenuListAdapter
import com.power.suntechpower.Models.DashboardMenuModel
import com.power.suntechpower.Models.Wallet_HistoryResponse
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityWalletBinding
import java.text.NumberFormat
import java.util.Locale

class Wallet_Activity : AppCompatActivity() {
    private var List: ArrayList<Wallet_HistoryResponse> = ArrayList()
    lateinit var binding : ActivityWalletBinding
    val amount = 5750209
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wallet)
        binding.totalamount.text =formatCurrency(amount.toDouble())
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

        binding.withdrawbutton.setOnClickListener {
            startActivity(Intent(this,Withdraw_form_Activity::class.java))
        }
        binding.updatebutton.setOnClickListener {
            startActivity(Intent(this,Update_Amount_Activity::class.java))
        }



        prepareNavMenuList()

        setTableData()
    }

    fun formatCurrency(amount: Double): String {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        return numberFormat.format(amount)
    }
    private fun prepareNavMenuList() {
        // Now it's safe to call clear() and add items to the menuList
        List.clear()

        List.add(Wallet_HistoryResponse("1","Credit Card","AU Small Finance Bank","AU LIT Credit Card","KARUN Kumar","KA@GMAIL.COM","*****B7","Pending"))
        List.add(Wallet_HistoryResponse("2","Credit Card","AU Small Finance Bank","AU LIT Credit Card","KARUN Kumar","KA@GMAIL.COM","*****B7","Pending"))
        List.add(Wallet_HistoryResponse("3","Credit Card","AU Small Finance Bank","AU LIT Credit Card","KARUN Kumar","KA@GMAIL.COM","*****B7","Pending"))
        List.add(Wallet_HistoryResponse("4","Credit Card","AU Small Finance Bank","AU LIT Credit Card","KARUN Kumar","KA@GMAIL.COM","*****B7","Pending"))
        List.add(Wallet_HistoryResponse("5","Credit Card","AU Small Finance Bank","AU LIT Credit Card","KARUN Kumar","KA@GMAIL.COM","*****B7","Pending"))
        List.add(Wallet_HistoryResponse("6","Credit Card","AU Small Finance Bank","AU LIT Credit Card","KARUN Kumar","KA@GMAIL.COM","*****B7","Pending"))
        List.add(Wallet_HistoryResponse("7","Credit Card","AU Small Finance Bank","AU LIT Credit Card","KARUN Kumar","KA@GMAIL.COM","*****B7","Pending"))
        List.add(Wallet_HistoryResponse("8","Credit Card","AU Small Finance Bank","AU LIT Credit Card","KARUN Kumar","KA@GMAIL.COM","*****B7","Pending"))
        List.add(Wallet_HistoryResponse("9","Credit Card","AU Small Finance Bank","AU LIT Credit Card","KARUN Kumar","KA@GMAIL.COM","*****B7","Pending"))
        List.add(Wallet_HistoryResponse("10","Credit Card","AU Small Finance Bank","AU LIT Credit Card","KARUN Kumar","KA@GMAIL.COM","*****B7","Pending"))

    }

    private fun setTableData(){
        for (person in List) {
            // Create a new TableRow
            val tableRow = TableRow(this)

            // Create TextViews for each field in the row
            val idTextView = TextView(this).apply {
                text = person.indexing
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f  // Increased text size
            }

            val nameTextView = TextView(this).apply {
                text = person.Transaction
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val ageTextView = TextView(this).apply {
                text = person.Request_Amount
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val addressTextView = TextView(this).apply {
                text = person.Transaction_Charge
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val emailTextView = TextView(this).apply {
                text = person.Request_Date
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val phoneTextView = TextView(this).apply {
                text = person.Admin_Response
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val statusTextView = TextView(this).apply {
                text = person.Response_date
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val countryTextView = TextView(this).apply {
                text = person.Status
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val divider = View(this)
            divider.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                1 // Set height of divider
            )
            divider.setBackgroundColor(Color.GRAY) // Divider color
            binding.wallettableLayout.tableLayout.addView(divider)

            // Add TextViews to the TableRow
            tableRow.addView(idTextView)
            tableRow.addView(nameTextView)
            tableRow.addView(ageTextView)
            tableRow.addView(addressTextView)
            tableRow.addView(emailTextView)
            tableRow.addView(phoneTextView)
            tableRow.addView(statusTextView)
            tableRow.addView(countryTextView)

            // Add TableRow to TableLayout
            binding.wallettableLayout.tableLayout.addView(tableRow)
        }
    }
}