package com.power.suntechpower.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.power.suntechpower.Adapters.AnnoucementAdapter
import com.power.suntechpower.BaseFragment
import com.power.suntechpower.Models.AnnoucementResponse
import com.power.suntechpower.databinding.FragmentAnnouncementBinding

class AnnouncementFragment : BaseFragment() {
    lateinit var binding : FragmentAnnouncementBinding
    private var annoucementlist: ArrayList<AnnoucementResponse> = ArrayList()
    private var annoucementAdapter: AnnoucementAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnnouncementBinding.inflate(inflater, container, false)


        prepareopenleadList()
        annoucementAdapter = AnnoucementAdapter(annoucementlist,requireContext())
        binding.notificationRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.notificationRecyclerview.adapter = annoucementAdapter

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Notify MainActivity to navigate to HomeFragment
                Exitdialogbox()
            }
        })

        return binding.root
    }

    private fun prepareopenleadList() {

        annoucementlist.clear()

        for (i in 0 until 20) {
            annoucementlist.add(
                AnnoucementResponse(
                    "New Offers only for you!",
                    "New Exciting Offers only for you! Grab it now",
                )
            )
        }
    }
}