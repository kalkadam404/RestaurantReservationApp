package com.example.restaurantreservation.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.restaurantreservation.R
import com.example.restaurantreservation.view.adapter.OnboardingPagerAdapter
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class OnboardingActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var skipButton: TextView
    private lateinit var nextArrow: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.onboarding_viewpager)
        skipButton = findViewById(R.id.skipButton)
        nextArrow = findViewById(R.id.nextArrow)
        viewPager.adapter = OnboardingPagerAdapter(this)


        val wormDotsIndicator = findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)
        wormDotsIndicator.attachTo(viewPager)

        skipButton.setOnClickListener {
            navigateToMainScreen()
        }

        nextArrow.setOnClickListener {
            if (viewPager.currentItem < 2) {
                viewPager.currentItem += 1
            } else {
                navigateToMainScreen()
            }
        }
    }

    private fun navigateToMainScreen() {
        val sharedPref = getSharedPreferences("onboarding", MODE_PRIVATE)
        sharedPref.edit()
            .putBoolean("onboarding_completed", true)
            .apply()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
