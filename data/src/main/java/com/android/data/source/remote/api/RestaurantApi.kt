package com.android.data.source.remote.api

import com.android.data.model.banner.BannerResponse
import com.android.data.model.product.ProductListResponse
import com.android.data.model.reservation.ReservationListResponse
import com.android.data.model.restaurant.RestaurantListResponse
import retrofit2.http.GET

interface RestaurantApi {
    @GET("api/v1/restaurants/")
    suspend fun fetchRestaurantList(): RestaurantListResponse

    @GET("api/v1/advertisements/banners/")
    suspend fun fetchAdvertisementList(): List<BannerResponse>

    @GET("api/v1/products/menu-items/")
    suspend fun fetchProductsList(): ProductListResponse

    @GET("api/v1/room/reservations/")
    suspend fun fetchReservationList(): ReservationListResponse



}