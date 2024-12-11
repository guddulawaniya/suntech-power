package com.power.suntechpower.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.power.suntechpower.BaseFragment
import com.power.suntechpower.R
import com.power.suntechpower.databinding.FragmentSettingBinding

class SettingFragment : BaseFragment() {

    lateinit var binding : FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Notify MainActivity to navigate to HomeFragment
                Exitdialogbox()
            }
        })


        return binding.root
    }
}