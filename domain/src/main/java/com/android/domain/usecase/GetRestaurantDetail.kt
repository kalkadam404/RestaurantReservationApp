package com.android.domain.usecase

import com.android.domain.model.Restaurant
import com.android.domain.repository.RestaurantDetailRepository
import com.android.domain.util.UseCase

class GetRestaurantDetail(
    private val repository: RestaurantDetailRepository
) : UseCase<Restaurant, GetRestaurantDetail.Params>() {

    data class Params(val restaurantId: Int, val force: Boolean)

    override suspend fun run(params: Params): Result<Restaurant> {
        return repository.getRestaurantDetail(params.restaurantId, params.force)
    }
}