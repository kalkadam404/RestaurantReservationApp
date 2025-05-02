package com.android.data.model.restaurant

import com.android.data.model.city.CityResponse

data class RestaurantDetailResponse2 (
    val id: Int,
    val name: String,
    val city: CityResponse,
    val photo: String

)