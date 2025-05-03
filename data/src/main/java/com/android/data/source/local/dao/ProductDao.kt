package com.android.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.data.model.product.ProductEntity
import com.android.data.model.restaurant.RestaurantEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM menu")
    suspend fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM menu WHERE id = :id")
    suspend fun getProductById(id: Int): ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productEntity: ProductEntity)
}