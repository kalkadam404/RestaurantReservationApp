package com.example.restaurantreservation.view.di

import com.android.data.repository.ProductRepositoryImpl
import com.android.data.source.local.DatabaseProvider
import com.android.data.source.local.ProductLocalDataSource
import com.android.data.source.local.RestaurantDatabase
import com.android.data.source.local.dao.ProductDao
import com.android.data.source.remote.api.RestaurantApi
import com.android.data.source.remote.api.RestaurantRemoteDataSource
import com.android.data.util.network.NetworkConfig
import com.android.data.util.network.NetworkManager
import com.android.domain.repository.ProductRepository
import com.android.domain.usecase.GetProductList
import com.android.domain.usecase.InsertProductList
import com.example.restaurantreservation.view.viewmodels.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val ProductModule = module {
    viewModel {
        ProductViewModel(
            getProductList = get<GetProductList>(),
            insertProductList = get<InsertProductList>()
        )
    }

    factory<GetProductList> {
        GetProductList(
            repository = get<ProductRepository>()
        )
    }

    factory<InsertProductList> {
        InsertProductList(
            repository = get<ProductRepository>()
        )
    }

    single<ProductRepository> {
        ProductRepositoryImpl(
            remoteDataSource = get<RestaurantRemoteDataSource>(),
            localDataSource = get<ProductLocalDataSource>()
        )
    }

    single<ProductLocalDataSource> {
        ProductLocalDataSource(
            dao = get<ProductDao>()
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

    single<ProductDao> {
        get<DatabaseProvider>().productDao
    }

    single<DatabaseProvider> {
        RestaurantDatabase(androidContext())
    }
}