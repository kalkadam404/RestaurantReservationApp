package com.android.data.source.remote.api

import com.android.data.model.RestaurantListResponse
import retrofit2.http.GET

interface RestaurantApi {
    @GET("api/v1/restaurants")
    suspend fun fetchRestaurantList(): RestaurantListResponse

}