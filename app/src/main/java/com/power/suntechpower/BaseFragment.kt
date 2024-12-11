package com.power.suntechpower

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.power.suntechpower.Util.UserPref
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
open class BaseFragment : Fragment() {

    @Inject
    lateinit var userPref: UserPref


    var dialog: Dialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    @SuppressLint("ResourceAsColor", "SuspiciousIndentation")
    protected fun showProgressDialog() {
        if (dialog == null)
            dialog = Dialog(requireContext())
        dialog!!.setContentView(R.layout.progress_dialog)
        dialog!!.setCancelable(false)
        //  dialog!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(droidninja.filepicker.R.color.transparent_black));

        if (dialog != null && !dialog!!.isShowing)
            dialog!!.show()
    }
    protected fun hideProgressDialog() {
        if (dialog != null && dialog!!.isShowing)
            dialog!!.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog != null && dialog!!.isShowing)
            dialog!!.dismiss()
    }



    fun changeLanguage(prefLanguage: String) {
        val locale = Locale(prefLanguage)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    fun snackbar(message: String) {
        Snackbar.make(requireActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }

    fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


  /*   fun navigateToHomeFragment() {
        val homeFragment = HomeFragment()
        val fragmentTransaction = parentFragmentManager.beginTransaction()

        // Replace the existing fragment (if any) with the new one
        fragmentTransaction.replace(R.id.fragment_container, homeFragment)

        // Commit the transaction
        fragmentTransaction.commit()
    }
*/

    fun Exitdialogbox() {

        val dialogView = MaterialAlertDialogBuilder(requireContext(), R.style.MyRounded_MaterialComponents_MaterialAlertDialog)
            .setView(R.layout.exit_dialog_box)  // Set the custom layout
            .show()
        dialogView.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



        val cancel_button = dialogView.findViewById<TextView>(R.id.cancel_button)
        val yesbutton = dialogView.findViewById<Button>(R.id.yesbutton)


        cancel_button!!.setOnClickListener {

            dialogView.dismiss() // Close the dialog
        }

        yesbutton?.setOnClickListener {
            requireActivity().finish()

        }
    }


}