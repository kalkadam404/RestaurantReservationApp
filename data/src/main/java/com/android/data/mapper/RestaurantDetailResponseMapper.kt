package com.android.data.mapper


import com.android.data.model.restaurant.RestaurantDetailsResponse
import com.android.data.model.restaurant.RestaurantEntity

import com.android.domain.model.Restaurant

val RestaurantDetailResponseMapper: (RestaurantDetailsResponse) -> Restaurant = { response ->
    Restaurant(
        id = response.id,
        imageRes = response.photo,
        name = response.name,
        restaurantPlace = response.city.name,
        opening = response.opening_time,
        closing = response.closing_time
    )
}

val localRestaurantDetailResponseMapper: (RestaurantEntity) -> Restaurant = { response ->
    Restaurant(
        id = response.id,
        imageRes = response.imageUrl,
        name = response.title,
        restaurantPlace = response.location,
        opening = response.opening,
        closing = response.closing
    )
}

val restaurantDetailToRestaurantDetailEntityMapper: (Restaurant) -> RestaurantEntity = { restaurant ->
    RestaurantEntity(
        id = restaurant.id,
        title = restaurant.name,
        imageUrl = restaurant.imageRes,
        opening = restaurant.opening!!,
        closing = restaurant.closing,
        location = restaurant.restaurantPlace
    )
}

