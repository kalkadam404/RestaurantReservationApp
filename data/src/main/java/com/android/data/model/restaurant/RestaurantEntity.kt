package com.android.data.model.restaurant
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Restaurant")
data class RestaurantEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val opening: String,
    val closing: String?,
    val location: String,
    @ColumnInfo("image_url") val imageUrl: String? = null
)