package com.example.restaurantreservation.view.activities

import android.os.Bundle
import android.util.Log

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.restaurantreservation.databinding.ActivityMainBinding

import com.example.restaurantreservation.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

//        navController = navHostFragment.navController

        if (navHostFragment != null) {
            navController = navHostFragment.navController
            binding.bottomNavigation.post {
                NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
            }
        } else {
            Log.e("MainActivity", "NavHostFragment is null")
        }
    }
}