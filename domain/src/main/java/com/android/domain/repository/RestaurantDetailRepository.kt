package com.android.domain.repository

import com.android.domain.model.Restaurant

interface RestaurantDetailRepository {
    suspend fun getRestaurantDetail(restaurantId: Int, force: Boolean): Result<Restaurant>
    suspend fun insertRestaurantDetail(restaurant: Restaurant): Result<Unit>
}