package com.android.data.model.product
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Menu")
data class ProductEntity (
    @PrimaryKey val id: Int,
    val restaurant_name: String,
    val restaurant_city_name: String,
    val menu_type_name: String,
    val name_ru: String?,
    val name_kz: String?,
    @ColumnInfo("image_url")val imageUrl: String?,
    val description_ru: String?,
    val description_kz: String?,
    val calories: Float?,
    val proteins: String?,
    val fats: String?,
    val carbohydrates: String?,
    val price: String?,
    val is_available: Boolean?
)