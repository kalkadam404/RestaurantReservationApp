package com.android.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Banner")
data class BannerEntity (
    @PrimaryKey val id: Int,
    val banner_name: String?,
    val subtitle: String?,
    val description: String?,
    @ColumnInfo("image_url")val imageUrl: String?,
    val position: String?,
    val color: String?,
)