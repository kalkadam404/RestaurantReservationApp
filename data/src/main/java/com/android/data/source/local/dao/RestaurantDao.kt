package com.android.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.data.model.restaurant.RestaurantEntity

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant")
    suspend fun getAll(): List<RestaurantEntity>

    @Query("SELECT * FROM restaurant WHERE id = :id")
    suspend fun getRestaurantById(id: Int): RestaurantEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(restaurants: List<RestaurantEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurantEntity: RestaurantEntity)
}