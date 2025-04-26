package com.android.data.source.local

import com.android.data.model.RestaurantEntity
import com.android.data.source.local.dao.RestaurantDao

class RestaurantLocalDataSource (private val dao: RestaurantDao){

    suspend fun fetchRestaurantList() = dao.getAll()

    suspend fun insertRestaurantList(restaurantList: List<RestaurantEntity>) = dao.insertAll(restaurantList)
}