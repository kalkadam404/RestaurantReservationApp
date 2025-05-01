package com.android.data.source.remote.api

import com.android.data.model.BannerResponse
import com.android.data.model.ProductListResponse
import com.android.data.model.RestaurantListResponse
import retrofit2.http.GET

interface RestaurantApi {
    @GET("api/v1/restaurants/")
    suspend fun fetchRestaurantList(): RestaurantListResponse

    @GET("api/v1/advertisements/banners/")
    suspend fun fetchAdvertisementList(): List<BannerResponse>

    @GET("api/v1/products/menu-items/")
    suspend fun fetchProductsList(): ProductListResponse

    @GET("api/v1/room")
    suspend fun fetchRoomList(): RestaurantListResponse



}