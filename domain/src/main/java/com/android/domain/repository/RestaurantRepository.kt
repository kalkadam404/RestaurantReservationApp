package com.android.domain.repository

import com.android.domain.model.Restaurant

interface RestaurantRepository {
    suspend fun getRestaurantList(force: Boolean): Result<List<Restaurant>>
    suspend fun insertRestaurantList(restaurantList: List<Restaurant>): Result<Unit>
}