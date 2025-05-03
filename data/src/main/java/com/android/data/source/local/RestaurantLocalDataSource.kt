package com.android.data.source.local

import com.android.data.model.product.ProductEntity
import com.android.data.model.restaurant.RestaurantEntity
import com.android.data.source.local.dao.RestaurantDao

class RestaurantLocalDataSource (private val dao: RestaurantDao){

    suspend fun fetchRestaurantList() = dao.getAll()

    suspend fun fetchRestaurantDetail(id: Int) = dao.getRestaurantById(id)

    suspend fun insertRestaurantDetail(restaurantEntity: RestaurantEntity) = dao.insert(restaurantEntity)

    suspend fun insertRestaurantList(restaurantList: List<RestaurantEntity>) = dao.insertAll(restaurantList)
}