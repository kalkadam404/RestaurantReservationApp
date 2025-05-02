package com.android.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.data.model.product.ProductTypeEntity

@Dao
interface ProductTypeDao {

    @Query("SELECT * FROM menutype")
    suspend fun getAll(): List<ProductTypeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(reservations: List<ProductTypeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reservationEntity: ProductTypeEntity)
}