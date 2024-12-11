package com.power.suntechpower

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.power.suntechpower.Activity.About_us_Activity
import com.power.suntechpower.Activity.Change_passsword_activity
import com.power.suntechpower.Activity.Dashboard_Activity
import com.power.suntechpower.Activity.Direct_member_Activity
import com.power.suntechpower.Activity.InCard_Activity
import com.power.suntechpower.Activity.Leads_Activity
import com.power.suntechpower.Activity.Level_income_report_Activity
import com.power.suntechpower.Activity.LoginActivity
import com.power.suntechpower.Activity.MyProfile_Activity
import com.power.suntechpower.Activity.Notification_Activity
import com.power.suntechpower.Activity.Privacy_policy_Activity
import com.power.suntechpower.Activity.Prospect_Activity
import com.power.suntechpower.Activity.Self_income_report_Activity
import com.power.suntechpower.Activity.Share_Activity
import com.power.suntechpower.Activity.Update_kyc_Activity
import com.power.suntechpower.Activity.Wallet_Activity
import com.power.suntechpower.Activity.downline_member_Activity
import com.power.suntechpower.Activity.tree_view_Activity
import com.power.suntechpower.Activity.update_bank_details
import com.power.suntechpower.Adapters.InnerMenuAdapter
import com.power.suntechpower.Adapters.MenuListAdapter
import com.power.suntechpower.Fragments.AnnouncementFragment
import com.power.suntechpower.Fragments.HelpFragment
import com.power.suntechpower.Fragments.HomeFragment
import com.power.suntechpower.Fragments.SettingFragment
import com.power.suntechpower.Models.DashboardMenuModel
import com.power.suntechpower.ViewModels.MaineViewModel
import com.power.suntechpower.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(),MenuListAdapter.OnOuterItemClickListener , InnerMenuAdapter.OnItemClickListener {

    private val viewModel: MaineViewModel by viewModels()

    private var menuList: ArrayList<DashboardMenuModel> = ArrayList()  // Initialize as an empty list
    lateinit var binding: ActivityMainBinding
    private var menuListAdapter: MenuListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.itemRecyclerview.layoutManager = LinearLayoutManager(this)

        binding.menuicon.setOnClickListener {
            binding.mainDrawerLayout.openDrawer(GravityCompat.START)
        }

        binding.notificationIcon.setOnClickListener {
            startActivity(Intent(this,Notification_Activity::class.java))
        }

        binding.drawerToolbar.navHeaderImage.setOnClickListener {
            startActivity(Intent(this,MyProfile_Activity::class.java))
        }



        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_nav -> {
                    changeStatusBarColor("#163300")
                    binding.toolbar.visibility = View.VISIBLE
                    loadFragment(HomeFragment())
                    true
                }
                R.id.announcement_nav -> {
                    changeStatusBarColor("#FFFFFFFF")
                    binding.toolbar.visibility = View.GONE
                    loadFragment(AnnouncementFragment())
                    true
                }
                R.id.help_nav -> {
                    changeStatusBarColor("#FFFFFFFF")
                    binding.toolbar.visibility = View.GONE
                    loadFragment(HelpFragment())
                    true
                }
                R.id.settings_nav -> {
                    changeStatusBarColor("#FFFFFFFF")
                    binding.toolbar.visibility = View.GONE
                    loadFragment(SettingFragment())
                    true
                }
                else -> false
            }
        }

        loadFragment(HomeFragment())
        prepareNavMenuList()
    }

    private fun changeStatusBarColor(colorHex: String) {
        // Check the API version and change the status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color
            window.statusBarColor = Color.parseColor(colorHex)
        }

        // For Android 6.0 (API 23) and above, change the status bar icons to light or dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Set the status bar icons to dark if you have a light background
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }



    private fun prepareNavMenuList() {

        menuList.clear()
        menuList.add(DashboardMenuModel(R.drawable.usericon,"My Leads",listOf("Open Leads", "Process Leads", "Approved Leads","Rejected Leads")))
        menuList.add(DashboardMenuModel(R.drawable.propectlist,"Prospect List",listOf()))
        menuList.add(DashboardMenuModel(R.drawable.wallet,"Wallet management",listOf()))
        menuList.add(DashboardMenuModel(R.drawable.geneology,"Geneology",listOf("Downline Member","Direct Member","Tree View")))
        menuList.add(DashboardMenuModel(R.drawable.earningmoney,"Earning Reports",listOf("Earning Dashboard", "Self Income Report", "Level Income Report")))
        menuList.add(DashboardMenuModel(R.drawable.termsandcondition,"Terms and Conditions",listOf()))
        menuList.add(DashboardMenuModel(R.drawable.earningmoney,"Privacy policy",listOf()))
        menuList.add(DashboardMenuModel(R.drawable.usericon,"My Profile",listOf("Manage Profile", "Update Bank details", "Visiting Card","Update KYC","Change Password")))
        menuList.add(DashboardMenuModel(R.drawable.shareicon,"Referral",listOf()))
        menuList.add(DashboardMenuModel(R.drawable.aboutus,"Contact Us",listOf()))
        menuList.add(DashboardMenuModel(R.drawable.logouticon,"Logout",listOf()))

        menuListAdapter = MenuListAdapter(menuList,this,this)
        binding.itemRecyclerview.adapter = menuListAdapter

    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // Replace the existing fragment (if any) with the new one
        fragmentTransaction.replace(R.id.framlayout, fragment)

        // Add the transaction to the back stack (optional)
        fragmentTransaction.addToBackStack(null)

        // Commit the transaction
        fragmentTransaction.commit()
    }

    override fun onItemClick(name: String) {
        when (name) {
            "Earning Dashboard" -> {
                val intent = Intent(this, Dashboard_Activity::class.java)
                startActivity(intent)
            }
            "Manage Profile" -> {
                val intent = Intent(this, MyProfile_Activity::class.java)
                startActivity(intent)
            }
            "Visiting Card" -> {
                val intent = Intent(this, InCard_Activity::class.java)
                startActivity(intent)
            }
            "Open Leads" -> {
                val intent = Intent(this, Leads_Activity::class.java)
                intent.putExtra("index",0)
                startActivity(intent)
            }
            "Process Leads" -> {
                val intent = Intent(this, Leads_Activity::class.java)
                intent.putExtra("index",1)
                startActivity(intent)
            }
            "Approved Leads" -> {
                val intent = Intent(this, Leads_Activity::class.java)
                intent.putExtra("index",2)
                startActivity(intent)
            }
            "Rejected Leads" -> {
                val intent = Intent(this, Leads_Activity::class.java)
                intent.putExtra("index",3)
                startActivity(intent)
            }
            "Downline Member" -> {
                val intent = Intent(this, downline_member_Activity::class.java)
                startActivity(intent)
            }
            "Direct Member" -> {
                val intent = Intent(this, Direct_member_Activity::class.java)
                startActivity(intent)
            }
            "Tree View" -> {
                val intent = Intent(this, tree_view_Activity::class.java)
                startActivity(intent)
            }
            "Self Income Report" -> {
                val intent = Intent(this, Self_income_report_Activity::class.java)
                startActivity(intent)
            }
            "Level Income Report" -> {
                val intent = Intent(this, Level_income_report_Activity::class.java)
                startActivity(intent)
            }
            "Change Password" -> {
                val intent = Intent(this, Change_passsword_activity::class.java)
                intent.putExtra("dashboard",true)
                startActivity(intent)
            }
            "Update Bank details" -> {
                val intent = Intent(this, update_bank_details::class.java)
                startActivity(intent)
            }
            "Update KYC" -> {
                val intent = Intent(this, Update_kyc_Activity::class.java)
                startActivity(intent)
            }

            else -> {
                // Handle other items
                toast(this,"invalid")
            }
        }
    }

    override fun onOuterItemClick(name: String) {
        when (name) {

            "Privacy policy" -> {
                val intent = Intent(this, Privacy_policy_Activity::class.java)

                startActivity(intent)
            }
            "Terms and Conditions" -> {
                val intent = Intent(this, Privacy_policy_Activity::class.java)
                intent.putExtra("check",true)
                startActivity(intent)
            }
            "Prospect List" -> {
                val intent = Intent(this, Prospect_Activity::class.java)
                startActivity(intent)
            }
            "Referral" -> {
                val intent = Intent(this, Share_Activity::class.java)
                startActivity(intent)
            }
            "Wallet management" -> {
                val intent = Intent(this, Wallet_Activity::class.java)
                startActivity(intent)
            }
            "Logout" -> {
                showCustomDialog()
            }
            "Contact Us" -> {
                val intent = Intent(this, About_us_Activity::class.java)
                startActivity(intent)
            }

            else -> {
                // Handle other items
                toast(this,"invalid")
            }
        }
    }

    private fun showCustomDialog() {

        val dialogView = MaterialAlertDialogBuilder(this, R.style.MyRounded_MaterialComponents_MaterialAlertDialog)
            .setView(R.layout.logout_dialog_box)  // Set the custom layout
            .show()
        dialogView.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



        val cancel_button = dialogView.findViewById<TextView>(R.id.cancel_button)
        val yesbutton = dialogView.findViewById<Button>(R.id.yesbutton)


        cancel_button!!.setOnClickListener {

            dialogView.dismiss() // Close the dialog
        }

        yesbutton?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }
    }

}
