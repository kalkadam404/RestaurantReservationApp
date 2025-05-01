package com.android.domain.model

data class Product(
    val id: Int,
    val name: String,
    val location: String,
    val menu_category: String,
    val ru_name: String?,
    val kz_name: String?,
    val imageUrl: String?,
    val ru_description: String?,
    val kz_description: String?,
    val calories: Float?,
    val proteins: Float?,
    val fats: Float?,
    val carbohydrates: Float?,
    val price: Float?,
    val available: Boolean?
)