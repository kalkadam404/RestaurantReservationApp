package com.android.data.source.remote.api

import com.android.data.model.BannerResponse

class RestaurantRemoteDataSource(private val api: RestaurantApi) {

    suspend fun fetchRestaurantList() = api.fetchRestaurantList()

    suspend fun fetchProductList() = api.fetchProductsList()

    suspend fun fetchAdvertisementList() = api.fetchAdvertisementList()
}