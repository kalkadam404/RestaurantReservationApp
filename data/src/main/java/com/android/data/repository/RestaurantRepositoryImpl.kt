package com.android.data.repository

import com.android.data.mapper.RestaurantResponseMapper
import com.android.data.mapper.localRestaurantResponseMapper
import com.android.data.mapper.restaurantToRestaurantEntityMapper
import com.android.data.source.local.RestaurantLocalDataSource
import com.android.data.source.remote.api.RestaurantRemoteDataSource
import com.android.data.util.repository.BaseRepository
import com.android.domain.model.Restaurant
import com.android.domain.repository.RestaurantRepository

class RestaurantRepositoryImpl (
    private val remoteDataSource: RestaurantRemoteDataSource,
    private val localDataSource: RestaurantLocalDataSource
) : BaseRepository(), RestaurantRepository {
    override suspend fun getRestaurantList(force: Boolean): Result<List<Restaurant>> = safeApiCall({
        if (force) {
            remoteDataSource.fetchRestaurantList().results.map(RestaurantResponseMapper)
        } else {
            val localRestaurantList = localDataSource.fetchRestaurantList()

            if (localRestaurantList.isEmpty()) {
                println("FetchType: Remote1")
                remoteDataSource.fetchRestaurantList().results.map(RestaurantResponseMapper)
            } else {
                println("FetchType: Local1")
                localRestaurantList.map(localRestaurantResponseMapper)
            }
        }
    })

    override suspend fun insertRestaurantList(restaurantList: List<Restaurant>): Result<Unit> = safeApiCall({
        localDataSource.insertRestaurantList(restaurantList.map(restaurantToRestaurantEntityMapper))
    })

}