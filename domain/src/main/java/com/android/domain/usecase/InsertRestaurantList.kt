package com.android.domain.usecase

import com.android.domain.model.Restaurant
import com.android.domain.repository.RestaurantRepository
import com.android.domain.util.UseCase

class InsertRestaurantList(
    private val repository: RestaurantRepository
) : UseCase<Unit, List<Restaurant>>() {

    override suspend fun run(params: List<Restaurant>): Result<Unit> {
        return repository.insertRestaurantList(params)
    }
}