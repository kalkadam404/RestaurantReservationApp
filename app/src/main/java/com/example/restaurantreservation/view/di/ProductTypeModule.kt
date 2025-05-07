package com.example.restaurantreservation.view.di


import com.android.data.repository.ProductTypeRepositoryImpl
import com.android.data.source.local.DatabaseProvider
import com.android.data.source.local.ProductTypeLocalDataSource
import com.android.data.source.local.RestaurantDatabase
import com.android.data.source.local.dao.ProductTypeDao
import com.android.data.source.remote.api.RestaurantApi
import com.android.data.source.remote.api.RestaurantRemoteDataSource
import com.android.data.util.network.NetworkConfig
import com.android.data.util.network.NetworkManager
import com.android.domain.repository.ProductTypeRepository
import com.android.domain.usecase.GetProductTypeList
import com.android.domain.usecase.InsertProductTypeList
import com.example.restaurantreservation.view.viewmodels.ProductTypeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val ProductTypeModule = module {
    viewModel {
        ProductTypeViewModel(
            getProductTypeList = get<GetProductTypeList>(),
            insertProductTypeList = get<InsertProductTypeList>()
        )
    }

    factory<GetProductTypeList> {
        GetProductTypeList(
            repository = get<ProductTypeRepository>()
        )
    }

    factory<InsertProductTypeList> {
        InsertProductTypeList(
            repository = get<ProductTypeRepository>()
        )
    }

    single<ProductTypeRepository> {
        ProductTypeRepositoryImpl(
            remoteDataSource = get<RestaurantRemoteDataSource>(),
            localDataSource = get<ProductTypeLocalDataSource>()
        )
    }

    single<ProductTypeLocalDataSource> {
        ProductTypeLocalDataSource(
            dao = get<ProductTypeDao>()
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

    single<ProductTypeDao> {
        get<DatabaseProvider>().productTypeDao
    }

    single<DatabaseProvider> {
        RestaurantDatabase(androidContext())
    }
}