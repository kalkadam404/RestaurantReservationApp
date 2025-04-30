package com.example.restaurantreservation.view

import android.app.Application
import com.example.restaurantreservation.view.di.AuthModule
import com.example.restaurantreservation.view.di.RestaurantModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(RestaurantModule, AuthModule))
        }
    }
}