package com.android.data.model.product

import com.android.data.model.restaurant.RestaurantDetailResponse

data class ProductResponse (
    val id: Int,
    val restaurant_details: RestaurantDetailResponse,
    val menu_type_details: ProductTypeResponse,
    val name_ru: String?,
    val name_kz: String?,
    val image: String?,
    val description_ru: String?,
    val description_kz: String?,
    val calories: Float?,
    val proteins: String?,
    val fats: String?,
    val carbohydrates: String?,
    val price: String?,
    val is_available: Boolean?
    )