package com.android.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.data.model.BannerEntity
import com.android.data.model.ProductEntity
import com.android.data.model.RestaurantEntity
import com.android.data.source.local.dao.BannerDao
import com.android.data.source.local.dao.ProductDao
import com.android.data.source.local.dao.RestaurantDao

@Database(entities = [RestaurantEntity::class, ProductEntity::class, BannerEntity::class], version = 4)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
    abstract fun productDao(): ProductDao
    abstract fun bannerDao(): BannerDao
}

private const val DATABASE_NAME = "restaurant_database"

class DatabaseProvider internal constructor(private val database: AppDatabase) {

    val restaurantDao: RestaurantDao
        get() = database.restaurantDao()

    val productDao: ProductDao
        get() = database.productDao()

    val bannerDao: BannerDao
        get() = database.bannerDao()

}

fun RestaurantDatabase(context: Context): DatabaseProvider {
    val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration() .build()

    return DatabaseProvider(database)
}