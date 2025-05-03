package com.android.data.model.restaurant

import com.android.data.model.city.CityResponse

data class RestaurantDetailsResponse (
    val id: Int,
    val name: String,
    val city: CityResponse,
    val opening_time: String?,
    val closing_time: String?,
    val iiko_organization_id: String?,
    val external_menu_id: String?,
    val price_category_id: String?,
    val department_id: String?,
    val photo: String?
)