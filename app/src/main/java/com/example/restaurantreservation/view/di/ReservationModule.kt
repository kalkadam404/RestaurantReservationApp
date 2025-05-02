package com.example.restaurantreservation.view.di


import com.android.data.repository.ReservationRepositoryImpl
import com.android.data.source.local.DatabaseProvider
import com.android.data.source.local.ReservationLocalDataSource
import com.android.data.source.local.RestaurantDatabase
import com.android.data.source.local.dao.ReservationDao
import com.android.data.source.remote.api.RestaurantApi
import com.android.data.source.remote.api.RestaurantRemoteDataSource
import com.android.data.util.network.NetworkConfig
import com.android.data.util.network.NetworkManager
import com.android.domain.repository.ReservationRepository
import com.android.domain.usecase.GetReservationList
import com.android.domain.usecase.InsertReservationList
import com.example.restaurantreservation.view.viewmodels.ReservationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val ReservationModule = module {
    viewModel {
        ReservationViewModel(
            getReservationList = get<GetReservationList>(),
            insertReservationList = get<InsertReservationList>()
        )
    }

    factory<GetReservationList> {
        GetReservationList(
            repository = get<ReservationRepository>()
        )
    }

    factory<InsertReservationList> {
        InsertReservationList(
            repository = get<ReservationRepository>()
        )
    }

    single<ReservationRepository> {
        ReservationRepositoryImpl(
            remoteDataSource = get<RestaurantRemoteDataSource>(),
            localDataSource = get<ReservationLocalDataSource>()
        )
    }

    single<ReservationLocalDataSource> {
        ReservationLocalDataSource(
            dao = get<ReservationDao>()
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

    single<ReservationDao> {
        get<DatabaseProvider>().reservationDao
    }

    single<DatabaseProvider> {
        RestaurantDatabase(androidContext())
    }
}