package com.power.suntechpower.Activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.power.suntechpower.R
import com.power.suntechpower.databinding.ActivityEnterAmountBinding
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class Enter_Amount_Activity : AppCompatActivity() {

    lateinit var binding: ActivityEnterAmountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_enter_amount)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color
            window.statusBarColor = Color.parseColor("#F3F9FF") // Light color for status bar
        }

        // For Android 6.0 (API 23) and above, change the status bar icons to light or dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Set the status bar icons to dark if you have a light background
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // Hide the keyboard when clicking on the EditText
        binding.inputamount.setOnClickListener {
            hideKeyboard()
        }

        // Back button listener
        binding.backicon.setOnClickListener {
            finish()
        }

        // Set keyboard listeners for number buttons
        setKeyboardListeners()


        binding.ContinueButtom.setOnClickListener {
            startActivity(Intent(this,withdrawal_Amount_greeting_Activity::class.java))
        }

        setCurrencyInputFormatter()

    }

    // Hide the keyboard function
    private fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun setKeyboardListeners() {
        // Handle button clicks for each key
        binding.num1.setOnClickListener { appendToInput(1) }
        binding.num2.setOnClickListener { appendToInput(2) }
        binding.num3.setOnClickListener { appendToInput(3) }
        binding.num4.setOnClickListener { appendToInput(4) }
        binding.num5.setOnClickListener { appendToInput(5) }
        binding.num6.setOnClickListener { appendToInput(6) }
        binding.num7.setOnClickListener { appendToInput(7) }
        binding.num8.setOnClickListener { appendToInput(8) }
        binding.num9.setOnClickListener { appendToInput(9) }
        binding.zero.setOnClickListener { appendToInput(0) }

        // Handle Clear button click
        binding.backspace.setOnClickListener {
            // Check if there's any text in the EditText
            val currentText = binding.inputamount.text.toString()
            if (currentText.isNotEmpty() && currentText.length!=1) {
                // Remove the last character from the input
                binding.inputamount.setText(currentText.substring(0, currentText.length - 1))

                // Move the cursor to the end of the updated text
                binding.inputamount.setSelection(binding.inputamount.text.length)
            }
        }

    }

    private fun appendToInput(value: Int) {

        binding.inputamount.append(value.toString())
    }

    fun setCurrencyInputFormatter() {
        // Add a TextWatcher to format the input as currency
        binding.inputamount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                // Not required, but can be used for other logic if needed
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                if (charSequence != null) {
                    // Remove non-numeric characters except decimal point
                    val cleanString = charSequence.toString().replace("[^\\d.]".toRegex(), "")

                    if (cleanString.isNotEmpty()) {
                        val amount = cleanString.toDoubleOrNull()
                        if (amount != null) {
                            // Format the number as currency (INR)
                            val formattedAmount = formatCurrency(amount)

                            // Temporarily remove the TextWatcher to avoid infinite loop
                            binding.inputamount.removeTextChangedListener(this)
                            binding.inputamount.setText(formattedAmount)
                            binding.inputamount.setSelection(formattedAmount.length) // Move cursor to the end
                            binding.inputamount.addTextChangedListener(this) // Re-add the TextWatcher
                        }
                    }
                }
            }

            override fun afterTextChanged(editable: Editable?) {
                // Not required here
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Get the current focus view (the EditText or other view receiving input)
        val view = currentFocus
        if (view != null && event.action == MotionEvent.ACTION_DOWN) {
            val rect = Rect()
            view.getGlobalVisibleRect(rect)
            // Check if the touch was outside the EditText
            if (!rect.contains(event.x.toInt(), event.y.toInt())) {
                // Hide the keyboard
                hideKeyboard()
            }
        }
        return super.onTouchEvent(event)
    }

    // Method to format the currency
    fun formatCurrency(amount: Double): String {
        // Using the Indian locale for currency formatting (₹)
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        return currencyFormat.format(amount)
    }
}
