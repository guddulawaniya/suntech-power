package com.power.suntechpower.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.power.suntechpower.Activity.OnboardingActivity
import com.power.suntechpower.R
import com.power.suntechpower.databinding.FragmentOnboardingBinding


class OnboardingFragment : Fragment() {

    lateinit var binding: FragmentOnboardingBinding

    companion object {
        // Create a new instance of the fragment with specific data
        fun newInstance(imageResId: Int,titlevisiblety: Boolean, buttontext: String, description: String): OnboardingFragment {
            val fragment = OnboardingFragment()
            val args = Bundle()
            args.putBoolean("hidetext", titlevisiblety)
            args.putString("button", buttontext)
            args.putInt("image", imageResId)
            args.putString("description", description)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)

        val imageResId = arguments?.getInt("image") ?: R.drawable.onboarding1
        val description = arguments?.getString("description") ?: "Explore the app."
        val buttontext = arguments?.getString("button") ?: "Next"
        val titlevisiblety = arguments?.getBoolean("hidetext") ?: false
        if (titlevisiblety){
            binding.linearLayout.visibility = View.INVISIBLE
            binding.onboardingTitle3.visibility = View.INVISIBLE
        }
        else
        {
            binding.linearLayout.visibility = View.VISIBLE
            binding.onboardingTitle3.visibility = View.VISIBLE

        }

        // Set the data to views
        binding.onboardingButton.text = buttontext
        binding.onboardingImage.setImageResource(imageResId)
        binding.onboardingDescription.text = description


        // Set button click listener to move to the next page or finish
        binding.onboardingButton.setOnClickListener {
            val nextFragment = activity as OnboardingActivity
            nextFragment.moveToNextPage()
        }
        binding.skipped.setOnClickListener{
            val nextFragment = activity as OnboardingActivity
            nextFragment.moveToNextPage()
        }

        return binding.root
    }

}