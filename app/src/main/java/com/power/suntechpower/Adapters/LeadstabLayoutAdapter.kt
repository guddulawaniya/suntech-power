package com.power.suntechpower.Adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.power.suntechpower.Fragments.Approved_LeadsFragment
import com.power.suntechpower.Fragments.OpenLeadsFragment
import com.power.suntechpower.Fragments.ProcessLeadFragment
import com.power.suntechpower.Fragments.RejectLeadFragment
import com.power.suntechpower.Models.ApproveLeadsResponse
import com.power.suntechpower.Models.OpensLeadsResponse
import com.power.suntechpower.Models.ProcessLeadsResponse
import com.power.suntechpower.Models.RejectLeadsResponse

class LeadstabLayoutAdapter(

    fragmentActivity: FragmentActivity,

    private val openleadList: List<OpensLeadsResponse>,
    private val processleadList: List<ProcessLeadsResponse>,
    private val approveleadList: List<ApproveLeadsResponse>,
    private val rejectleadList: List<RejectLeadsResponse>

) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        // Return a different fragment based on the tab position
        val bundle = Bundle()
        when (position) {
            0 -> {
                // Open Leads
                val fragment = OpenLeadsFragment()
                bundle.putSerializable("openleads", ArrayList(openleadList))
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                // Process Leads
                val fragment = ProcessLeadFragment()
                bundle.putSerializable("processleads", ArrayList(processleadList))
                fragment.arguments = bundle
                return fragment
            }
            2 -> {
                // Approved Leads
                val fragment = Approved_LeadsFragment()
                bundle.putSerializable("approvedleads", ArrayList(approveleadList))
                fragment.arguments = bundle
                return fragment
            }
            3 -> {
                // Reject Leads
                val fragment = RejectLeadFragment()
                bundle.putSerializable("rejectleads", ArrayList(rejectleadList))
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                // Default fragment, in case of an unexpected position
                return OpenLeadsFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return 4 // Number of tabs or pages (adjust accordingly)
    }
}
