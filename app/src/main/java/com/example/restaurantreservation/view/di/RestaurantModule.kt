package com.example.restaurantreservation.view.di


import com.android.data.repository.RestaurantRepositoryImpl
import com.android.data.source.remote.api.RestaurantApi
import com.android.data.source.remote.api.RestaurantRemoteDataSource
import com.android.data.util.network.NetworkConfig
import com.android.data.util.network.NetworkManager
import com.android.data.source.local.DatabaseProvider
import com.android.data.source.local.RestaurantDatabase
import com.android.data.source.local.RestaurantLocalDataSource
import com.android.data.source.local.dao.RestaurantDao
import com.android.domain.repository.RestaurantRepository
import com.android.domain.usecase.GetRestaurantList
import com.android.domain.usecase.InsertRestaurantList
import com.example.restaurantreservation.view.viewmodels.RestaurantViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val RestaurantModule = module {
    viewModel {
        RestaurantViewModel(
            getRestaurantList = get<GetRestaurantList>(),
            insertRestaurantList = get<InsertRestaurantList>()
        )
    }

    factory<GetRestaurantList> {
        GetRestaurantList(
            repository = get<RestaurantRepository>()
        )
    }

    factory<InsertRestaurantList> {
        InsertRestaurantList(
            repository = get<RestaurantRepository>()
        )
    }

    single<RestaurantRepository> {
        RestaurantRepositoryImpl(
            remoteDataSource = get<RestaurantRemoteDataSource>(),
            localDataSource = get<RestaurantLocalDataSource>()
        )
    }

    single<RestaurantLocalDataSource> {
        RestaurantLocalDataSource(
            dao = get<RestaurantDao>()
        )
    }

    single<RestaurantRemoteDataSource> {
        RestaurantRemoteDataSource(
            api = get<RestaurantApi>()
        )
    }

    single<RestaurantApi> {
        NetworkManager.createService(
            config = get<NetworkConfig>()
        )
    }

    single<NetworkConfig> {
        NetworkConfig(
            baseUrl = "http://10.0.2.2:8000/",
            timeout = 60L,
            interceptors = listOf()
        )
    }

    single<RestaurantDao> {
        get<DatabaseProvider>().restaurantDao
    }

    single<DatabaseProvider> {
        RestaurantDatabase(androidContext())
    }
}