package com.example.restaurantreservation.view.di


import com.android.data.repository.BannerRepositoryImpl
import com.android.data.source.local.BannerLocalDataSource
import com.android.data.source.local.DatabaseProvider
import com.android.data.source.local.RestaurantDatabase
import com.android.data.source.local.dao.BannerDao
import com.android.data.source.remote.api.RestaurantApi
import com.android.data.source.remote.api.RestaurantRemoteDataSource
import com.android.data.util.network.NetworkConfig
import com.android.data.util.network.NetworkManager
import com.android.domain.repository.BannerRepository
import com.android.domain.usecase.GetBannerList
import com.android.domain.usecase.InsertBannerList
import com.example.restaurantreservation.view.viewmodels.BannerViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val BannerModule = module {
    viewModel {
        BannerViewModel(
            getBannerList = get<GetBannerList>(),
            insertBannerList = get<InsertBannerList>()
        )
    }

    factory<GetBannerList> {
        GetBannerList(
            repository = get<BannerRepository>()
        )
    }

    factory<InsertBannerList> {
        InsertBannerList(
            repository = get<BannerRepository>()
        )
    }

    single<BannerRepository> {
        BannerRepositoryImpl(
            remoteDataSource = get<RestaurantRemoteDataSource>(),
            localDataSource = get<BannerLocalDataSource>()
        )
    }

    single<BannerLocalDataSource> {
        BannerLocalDataSource(
            dao = get<BannerDao>()
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

    single<BannerDao> {
        get<DatabaseProvider>().bannerDao
    }

    single<DatabaseProvider> {
        RestaurantDatabase(androidContext())
    }
}