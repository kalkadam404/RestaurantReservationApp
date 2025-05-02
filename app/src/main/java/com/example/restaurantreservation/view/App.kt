package com.example.restaurantreservation.view

import android.app.Application
import com.example.restaurantreservation.view.di.BannerModule
import com.example.restaurantreservation.view.di.ProductModule
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.example.restaurantreservation.view.di.RestaurantModule
import com.example.restaurantreservation.view.di.AuthModule

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(RestaurantModule, ProductModule, BannerModule, AuthModule))
        }
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    }
}