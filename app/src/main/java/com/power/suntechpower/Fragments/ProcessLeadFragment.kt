package com.power.suntechpower.Fragments

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import com.power.suntechpower.Models.ProcessLeadsResponse
import com.power.suntechpower.databinding.FragmentProcessLeadBinding

class ProcessLeadFragment : Fragment() {

    lateinit var binding : FragmentProcessLeadBinding
    private lateinit var processleadList: List<ProcessLeadsResponse>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProcessLeadBinding.inflate(inflater, container, false)

        arguments?.let {
            processleadList = it.getSerializable("processleads") as List<ProcessLeadsResponse>
        }
        setTableData()
        return binding.root
    }



    private fun setTableData(){
        for (person in processleadList) {
            // Create a new TableRow
            val tableRow = TableRow(requireContext())

            // Create TextViews for each field in the row
            val idTextView = TextView(requireContext()).apply {
                text = person.sno
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f  // Increased text size
            }

            val nameTextView = TextView(requireContext()).apply {
                text = person.Servicetype
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val ageTextView = TextView(requireContext()).apply {
                text = person.Service_provider
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val addressTextView = TextView(requireContext()).apply {
                text = person.Service_name
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val emailTextView = TextView(requireContext()).apply {
                text = person.Full_name
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val phoneTextView = TextView(requireContext()).apply {
                text = person.Email
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val statusTextView = TextView(requireContext()).apply {
                text = person.Pan
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val countryTextView = TextView(requireContext()).apply {
                text = person.Status
                setPadding(25, 50, 25, 50)
                gravity = Gravity.CENTER
                textSize = 18f
            }

            val divider = View(requireContext())
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

            // Add TableRow to TableLayout
            binding.tableLayout.addView(tableRow)
        }
    }

}