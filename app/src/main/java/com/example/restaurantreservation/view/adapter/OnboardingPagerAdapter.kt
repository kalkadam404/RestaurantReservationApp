package com.example.restaurantreservation.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.restaurantreservation.view.fragment.OnboardingFragment1
import com.example.restaurantreservation.view.fragment.OnboardingFragment2
import com.example.restaurantreservation.view.fragment.OnboardingFragment3

class OnboardingPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment1()
            1 -> OnboardingFragment2()
            else -> OnboardingFragment3()
        }
    }
}
