package com.android.domain.usecase

import com.android.domain.model.Restaurant
import com.android.domain.repository.RestaurantRepository
import com.android.domain.util.UseCase


class GetRestaurantList (
    private val repository: RestaurantRepository
) : UseCase<List<Restaurant>, Boolean>() {

    override suspend fun run(params: Boolean): Result<List<Restaurant>> {
        return repository.getRestaurantList(params)
    }
}