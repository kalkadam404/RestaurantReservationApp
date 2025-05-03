package com.example.restaurantreservation.view.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.restaurantreservation.databinding.ActivityMainBinding
import com.example.restaurantreservation.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.crashlytics.FirebaseCrashlytics

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

        if (navHostFragment != null) {
            navController = navHostFragment.navController

            // Связываем FAB с переходом на QR-фрагмент
//            binding.fab.setOnClickListener {
//                // Сохраняем текущий выбранный элемент в меню перед переходом
//                val currentItemId = binding.bottomNavigation.selectedItemId
//                navController.navigate(R.id.qrScannerFragment)
//                // Не меняем выбранный элемент меню после перехода
//                binding.bottomNavigation.selectedItemId = currentItemId
//            }

            // Настраиваем обработчик нажатий на элементы меню
            binding.bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.home_fragment -> {
                        // Если мы уже на главном экране, то ничего не делаем
                        if (navController.currentDestination?.id != R.id.home_fragment) {
                            navController.navigate(R.id.home_fragment)
                        }
                        true
                    }
                    R.id.book_fragment -> {
                        if (navController.currentDestination?.id != R.id.book_fragment) {
                            navController.navigate(R.id.book_fragment)
                        }
                        true
                    }
                    R.id.profile_fragment -> {
                        if (navController.currentDestination?.id != R.id.profile_fragment) {
                            navController.navigate(R.id.profile_fragment)
                        }
                        true
                    }
                    R.id.menu_fragment -> {
                        if (navController.currentDestination?.id != R.id.menu_fragment) {
                            navController.navigate(R.id.menu_fragment)
                        }
                        true
                    }
                    // Добавьте другие пункты меню по необходимости
                    else -> false
                }
            }

            binding.fab.setOnClickListener {

                Toast.makeText(this, "FAB clicked", Toast.LENGTH_SHORT).show()
                try {
                    navController.navigate(R.id.qrScannerFragment)
                } catch (e: Exception) {
                    Toast.makeText(this, "Navigation Error: ${e.message}", Toast.LENGTH_LONG).show()
                    Log.e("MainActivity", "Navigation Error", e)
                }
            }

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.restaurantDetailFragment, R.id.loginFragment, R.id.registerFragment -> {
                        binding.bottomNavigation.visibility = BottomNavigationView.GONE
                        binding.bottomAppBar.visibility = View.GONE
                        binding.fab.hide()
                    }
                    R.id.qrScannerFragment -> {
                        // QR Scanner открыт, оставляем текущий выбранный пункт меню
                        binding.bottomNavigation.visibility = BottomNavigationView.VISIBLE
                        binding.bottomAppBar.visibility = View.VISIBLE
                        binding.fab.show()
                    }
                    else -> {
                        binding.bottomNavigation.visibility = BottomNavigationView.VISIBLE
                        binding.bottomAppBar.visibility = View.VISIBLE
                        binding.fab.show()

                        // Синхронизируем выбранный пункт меню с текущим фрагментом
                        when (destination.id) {
                            R.id.home_fragment -> binding.bottomNavigation.selectedItemId = R.id.home_fragment
                            R.id.book_fragment -> binding.bottomNavigation.selectedItemId = R.id.book_fragment
                            R.id.profile_fragment -> binding.bottomNavigation.selectedItemId = R.id.profile_fragment
                            R.id.menu_fragment -> binding.bottomNavigation.selectedItemId = R.id.menu_fragment
                            // Добавьте другие фрагменты по необходимости
                        }
                    }
                }
            }
        } else {
            Log.e("MainActivity", "NavHostFragment is null")
        }
    }
}