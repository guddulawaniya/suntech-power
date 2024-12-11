package com.power.suntechpower.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.power.suntechpower.Adapters.AnnoucementAdapter
import com.power.suntechpower.Adapters.HelpAdapter
import com.power.suntechpower.BaseFragment
import com.power.suntechpower.Models.AnnoucementResponse
import com.power.suntechpower.R
import com.power.suntechpower.databinding.FragmentHelpBinding
import com.power.suntechpower.databinding.FragmentHomeBinding

class HelpFragment : BaseFragment() {

    lateinit var binding : FragmentHelpBinding
    private var helplist: ArrayList<String> = ArrayList()
    private var helpAdapter: HelpAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHelpBinding.inflate(inflater, container, false)
        prepareopenleadList()
        helpAdapter = HelpAdapter(helplist,requireContext())
        binding.helprecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.helprecyclerview.adapter = helpAdapter

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Notify MainActivity to navigate to HomeFragment
                Exitdialogbox()
            }
        })

        return binding.root
    }

    private fun prepareopenleadList() {

        helplist.clear()

        helplist.add("Open Ticket")
        helplist.add("View Ticket Response")

    }
}