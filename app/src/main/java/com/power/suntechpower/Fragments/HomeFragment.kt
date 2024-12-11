package com.power.suntechpower.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.power.suntechpower.Activity.About_us_Activity
import com.power.suntechpower.Activity.Dashboard_Activity
import com.power.suntechpower.Activity.Leads_Activity
import com.power.suntechpower.Activity.Share_Activity
import com.power.suntechpower.Activity.Wallet_Activity
import com.power.suntechpower.BaseFragment
import com.power.suntechpower.databinding.FragmentHomeBinding
import java.text.NumberFormat
import java.util.Locale

class HomeFragment : BaseFragment() {

    lateinit var binding: FragmentHomeBinding
    val amount = 5750209

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.totalamount.text =formatCurrency(amount.toDouble())

        binding.dashboard.setOnClickListener {
            startActivity(Intent(requireContext(),Dashboard_Activity::class.java))
        }

        binding.referralcard.setOnClickListener {
            startActivity(Intent(requireContext(),Share_Activity::class.java))
        }
        binding.wallet.setOnClickListener {
            startActivity(Intent(requireContext(),Wallet_Activity::class.java))
        }

        binding.openleads.setOnClickListener {
            startActivity(Intent(requireContext(),Leads_Activity::class.java))
        }
        binding.contactuscard.setOnClickListener {
            startActivity(Intent(requireContext(),About_us_Activity::class.java))
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Notify MainActivity to navigate to HomeFragment
                Exitdialogbox()
            }
        })

        return binding.root
    }

    fun formatCurrency(amount: Double): String {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        return numberFormat.format(amount)
    }
}