package com.example.restaurantreservation.view

import android.app.Application
import com.example.restaurantreservation.view.di.ProductModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.example.restaurantreservation.view.di.RestaurantModule


class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(RestaurantModule, ProductModule))
        }
    }
}