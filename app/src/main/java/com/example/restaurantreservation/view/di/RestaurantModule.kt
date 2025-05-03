package com.example.restaurantreservation.view.di


import com.android.data.repository.RestaurantRepositoryImpl
import com.android.data.repository.RestaurantDetailRepositoryImpl
import com.android.data.source.remote.api.RestaurantApi
import com.android.data.source.remote.api.RestaurantRemoteDataSource
import com.android.data.util.network.NetworkConfig
import com.android.data.util.network.NetworkManager
import com.android.data.source.local.DatabaseProvider
import com.android.data.source.local.RestaurantDatabase
import com.android.data.source.local.RestaurantLocalDataSource
import com.android.data.source.local.dao.RestaurantDao
import com.android.domain.repository.RestaurantDetailRepository
import com.android.domain.repository.RestaurantRepository
import com.android.domain.usecase.GetRestaurantDetail
import com.android.domain.usecase.GetRestaurantList
import com.android.domain.usecase.InsertRestaurantDetail
import com.android.domain.usecase.InsertRestaurantList
import com.example.restaurantreservation.view.viewmodels.RestaurantViewModel
import com.example.restaurantreservation.view.viewmodels.RestaurantDetailsViewModel
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

    viewModel {
        RestaurantDetailsViewModel(
            getRestaurantDetail = get<GetRestaurantDetail>(),
            insertRestaurantDetail = get<InsertRestaurantDetail>()
        )
    }

    factory<GetRestaurantList> {
        GetRestaurantList(
            repository = get<RestaurantRepository>()
        )
    }

    factory<GetRestaurantDetail> {
        GetRestaurantDetail(
            repository = get<RestaurantDetailRepository>()
        )
    }

    factory<InsertRestaurantList> {
        InsertRestaurantList(
            repository = get<RestaurantRepository>()
        )
    }

    factory<InsertRestaurantDetail> {
        InsertRestaurantDetail(
            repository = get<RestaurantDetailRepository>()
        )
    }

    single<RestaurantRepository> {
        RestaurantRepositoryImpl(
            remoteDataSource = get<RestaurantRemoteDataSource>(),
            localDataSource = get<RestaurantLocalDataSource>()
        )
    }

    single<RestaurantDetailRepository> {
        RestaurantDetailRepositoryImpl(
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
            baseUrl = "https://epicure-wvby.onrender.com/",
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