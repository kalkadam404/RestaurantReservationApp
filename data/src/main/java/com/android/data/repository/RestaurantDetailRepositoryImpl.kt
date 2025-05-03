package com.android.data.repository

import com.android.domain.repository.RestaurantDetailRepository

import com.android.data.mapper.RestaurantDetailResponseMapper
import com.android.data.mapper.localRestaurantDetailResponseMapper
import com.android.data.mapper.restaurantDetailToRestaurantDetailEntityMapper
import com.android.data.source.local.RestaurantLocalDataSource
import com.android.data.source.remote.api.RestaurantRemoteDataSource
import com.android.data.util.repository.BaseRepository
import com.android.domain.model.Restaurant


class RestaurantDetailRepositoryImpl(
    private val remoteDataSource: RestaurantRemoteDataSource,
    private val localDataSource: RestaurantLocalDataSource
) : BaseRepository(), RestaurantDetailRepository {

    override suspend fun getRestaurantDetail(id: Int, force: Boolean): Result<Restaurant> = safeApiCall({
        if (force) {
            RestaurantDetailResponseMapper(remoteDataSource.fetchRestaurantDetail(id))
        } else {
            // Fetch from local DB
            val localRestaurant = localDataSource.fetchRestaurantDetail(id)

            run {
                println("FetchType: Local")
                // Map the local entity to Restaurant
                localRestaurantDetailResponseMapper(localRestaurant)
            }
        }
    })

    override suspend fun insertRestaurantDetail(restaurant: Restaurant): Result<Unit> = safeApiCall({
        // Insert Restaurant mapped to RestaurantEntity into the local database
        localDataSource.insertRestaurantDetail(restaurantDetailToRestaurantDetailEntityMapper(restaurant))
    })
}
