package com.android.data.source.remote.api

class RestaurantRemoteDataSource(private val api: RestaurantApi) {

    suspend fun fetchRestaurantList() = api.fetchRestaurantList()

    suspend fun fetchProductList() = api.fetchProductsList()
}