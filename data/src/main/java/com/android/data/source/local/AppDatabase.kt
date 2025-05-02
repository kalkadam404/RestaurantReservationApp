package com.android.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.data.model.banner.BannerEntity
import com.android.data.model.product.ProductEntity
import com.android.data.model.reservation.ReservationEntity
import com.android.data.model.restaurant.RestaurantEntity
import com.android.data.source.local.dao.BannerDao
import com.android.data.source.local.dao.ProductDao
import com.android.data.source.local.dao.ReservationDao
import com.android.data.source.local.dao.RestaurantDao

@Database(entities = [
    RestaurantEntity::class,
    ProductEntity::class,
    BannerEntity::class,
    ReservationEntity::class],
    version = 5)

internal abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
    abstract fun productDao(): ProductDao
    abstract fun bannerDao(): BannerDao
    abstract fun reservationDao(): ReservationDao
}

private const val DATABASE_NAME = "restaurant_database"

class DatabaseProvider internal constructor(private val database: AppDatabase) {

    val restaurantDao: RestaurantDao
        get() = database.restaurantDao()

    val productDao: ProductDao
        get() = database.productDao()

    val bannerDao: BannerDao
        get() = database.bannerDao()

    val reservationDao: ReservationDao
        get() = database.reservationDao()

}

fun RestaurantDatabase(context: Context): DatabaseProvider {
    val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration() .build()

    return DatabaseProvider(database)
}