package com.android.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.data.model.banner.BannerEntity

@Dao
interface BannerDao {

    @Query("SELECT * FROM banner")
    suspend fun getAll(): List<BannerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(banners: List<BannerEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bannerEntity: BannerEntity)
}