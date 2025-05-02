package com.android.data.model.product

import com.android.data.model.restaurant.RestaurantDetailResponse

data class ProductResponse (
    val id: Int,
    val restaurant_details: RestaurantDetailResponse,
    val menu_type_details: MenuTypeResponse,
    val name_ru: String?,
    val name_kz: String?,
    val image: String?,
    val description_ru: String?,
    val description_kz: String?,
    val calories: Float?,
    val proteins: Float?,
    val fats: Float?,
    val carbohydrates: Float?,
    val price: Float?,
    val is_available: Boolean?
    )