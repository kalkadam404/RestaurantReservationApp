package com.android.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.data.model.RestaurantEntity
import com.android.data.source.local.dao.RestaurantDao

@Database(entities = [RestaurantEntity::class], version = 2)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}

private const val DATABASE_NAME = "restaurant_database"

class DatabaseProvider internal constructor(private val database: AppDatabase) {

    val restaurantDao: RestaurantDao
        get() = database.restaurantDao()
}

fun RestaurantDatabase(context: Context): DatabaseProvider {
    val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration() .build()

    return DatabaseProvider(database)
}