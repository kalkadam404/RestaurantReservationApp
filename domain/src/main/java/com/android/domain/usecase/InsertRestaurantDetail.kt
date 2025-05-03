package com.android.domain.usecase

import com.android.domain.model.Restaurant
import com.android.domain.repository.RestaurantDetailRepository
import com.android.domain.util.UseCase

class InsertRestaurantDetail(
    private val repository: RestaurantDetailRepository
) : UseCase<Unit, InsertRestaurantDetail.Params>() {

    // Params will hold the single Product that needs to be inserted
    data class Params(val restaurant: Restaurant)

    override suspend fun run(params: Params): Result<Unit> {
        return repository.insertRestaurantDetail(params.restaurant)
    }
}
