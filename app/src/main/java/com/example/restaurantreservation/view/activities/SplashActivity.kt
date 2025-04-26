package com.example.restaurantreservation.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("onboarding", MODE_PRIVATE)
        val isOnboardingCompleted = sharedPref.getBoolean("onboarding_completed", false)

        if (isOnboardingCompleted) {
            // Если онбординг уже пройден — открываем MainActivity
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            // Иначе — показываем онбординг
            startActivity(Intent(this, OnboardingActivity::class.java))
        }
        finish() // Чтобы SplashActivity закрылась
    }
}
