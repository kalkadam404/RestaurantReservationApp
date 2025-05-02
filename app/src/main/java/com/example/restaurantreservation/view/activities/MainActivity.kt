package com.example.restaurantreservation.view.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.restaurantreservation.databinding.ActivityMainBinding
import com.example.restaurantreservation.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.android.material.badge.BadgeDrawable

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val bottomNavigationView = binding.bottomNavigation
        val badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.book_fragment)
        badgeDrawable.setNumber(4)
        badgeDrawable.setVisible(true)

        binding.fab.setOnClickListener {
            val navController = findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.action_global_qrScannerFragment)
        }

        try {
            throw RuntimeException("Тестовая ошибка для Crashlytics")
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }

        if (navHostFragment != null) {
            navController = navHostFragment.navController
            binding.bottomNavigation.post {
                NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
            }

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.restaurantDetailFragment, R.id.loginFragment, R.id.registerFragment -> {
                        binding.bottomNavigation.visibility = BottomNavigationView.GONE
                        binding.bottomAppBar.visibility = View.GONE
                        binding.fab.hide()
                    }
                    else -> {
                        binding.bottomNavigation.visibility = BottomNavigationView.VISIBLE
                        binding.bottomAppBar.visibility = View.VISIBLE
                        binding.fab.show()
                    }
                }
            }
        } else {
            Log.e("MainActivity", "NavHostFragment is null")
        }
    }
}