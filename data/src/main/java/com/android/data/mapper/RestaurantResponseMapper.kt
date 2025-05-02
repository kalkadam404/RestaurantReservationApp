package com.android.data.mapper

import com.android.data.model.restaurant.RestaurantEntity
import com.android.data.model.restaurant.RestaurantResponse
import com.android.domain.model.Restaurant

val RestaurantResponseMapper: (RestaurantResponse) -> Restaurant = { response ->
    Restaurant(
        id = response.id.toString(),
        imageRes = response.photo,
        name = response.name,
        restaurantPlace = response.city.name,
        opening = response.opening_time,
        closing = response.closing_time
    )
}

val localRestaurantResponseMapper: (RestaurantEntity) -> Restaurant = { response ->
    Restaurant(
        id = response.id,
        imageRes = response.imageUrl,
        name = response.title,
        restaurantPlace = response.location,
        opening = response.opening,
        closing = response.closing
    )
}

val restaurantToRestaurantEntityMapper: (Restaurant) -> RestaurantEntity = { restaurant ->
    RestaurantEntity(
        id = restaurant.id,
        title = restaurant.name,
        imageUrl = restaurant.imageRes,
        opening = restaurant.opening!!,
        closing = restaurant.closing,
        location = restaurant.restaurantPlace
    )
}

