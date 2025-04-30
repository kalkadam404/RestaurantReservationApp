package com.example.restaurantreservation.view.di

import com.android.data.repository.AuthRepositoryImpl
import com.android.data.source.remote.api.AuthApi
import com.android.data.source.remote.api.AuthRemoteDataSource
import com.android.data.util.TokenManager
import com.android.data.util.network.AuthInterceptor
import com.android.data.util.network.NetworkManager
import com.android.domain.repository.AuthRepository
import com.android.domain.usecase.IsUserLoggedInUseCase
import com.android.domain.usecase.LoginUseCase
import com.android.domain.usecase.RegisterUseCase
import com.example.restaurantreservation.view.viewmodels.AuthViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AuthModule = module {
    // ViewModels
    viewModel { AuthViewModel(get(), get()) }

    // UseCases
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { IsUserLoggedInUseCase(get()) }

    // Repository
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    // DataSources
    single { AuthRemoteDataSource(get()) }

    // API
    single<AuthApi> { NetworkManager.createService(get()) }

    // Util
    single { TokenManager(androidContext()) }
    single { AuthInterceptor(get()) }
}