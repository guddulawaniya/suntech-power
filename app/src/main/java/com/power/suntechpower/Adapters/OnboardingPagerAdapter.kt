package com.power.suntechpower.Adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.power.suntechpower.Activity.OnboardingActivity
import com.power.suntechpower.Fragments.OnboardingFragment
import com.power.suntechpower.R

class OnboardingPagerAdapter(fragmentActivity: OnboardingActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3 // You can change this to the number of onboarding screens you have

    override fun createFragment(position: Int): Fragment {
        // Provide different data for each page

        return when (position) {
            0 -> OnboardingFragment.newInstance(
                R.drawable.onboarding3,
                false,
                "Let’s Get Started ",
                "Budget smarter, Save effortlessly, Invest wisely."
            )
            1 -> OnboardingFragment.newInstance(
                R.drawable.onboarding2,
                true,
                "Next",
                "Track expenses, Set goals, Achieve financial success"
            )
            2 -> OnboardingFragment.newInstance(
                R.drawable.onboarding1,
                true,
                "Next",
                "Empower your finances with powerful tools and insights."
            )
            else -> OnboardingFragment.newInstance(
                R.drawable.onboarding3,
                true,
                "Next",
                "Explore the features of our app!"
            )
        }
    }
}
