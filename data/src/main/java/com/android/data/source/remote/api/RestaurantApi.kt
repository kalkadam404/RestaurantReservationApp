package com.android.data.source.remote.api

import com.android.data.model.banner.BannerResponse
import com.android.data.model.product.ProductDetailsResponse
import com.android.data.model.product.ProductListResponse
import com.android.data.model.product.ProductTypeListResponse
import com.android.data.model.reservation.ReservationListResponse
import com.android.data.model.restaurant.RestaurantDetailsResponse
import com.android.data.model.restaurant.RestaurantListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantApi {
    @GET("api/v1/restaurants/")
    suspend fun fetchRestaurantList(): RestaurantListResponse

    @GET("api/v1/restaurants/{id}")
    suspend fun fetchRestaurantDetails(@Path("id") restaurantId: Int): RestaurantDetailsResponse

    @GET("api/v1/advertisements/banners/")
    suspend fun fetchAdvertisementList(): List<BannerResponse>

    @GET("api/v1/products/menu-items/")
    suspend fun fetchProductsList(): ProductListResponse

    @GET("api/v1/products/menu-items/{id}")
    suspend fun fetchProductDetails(@Path("id") productId: Int): ProductDetailsResponse

    @GET("api/v1/room/reservations/")
    suspend fun fetchReservationList(): ReservationListResponse

    @GET("api/v1/products/menu-types/")
    suspend fun fetchProductTypeList(): ProductTypeListResponse






}