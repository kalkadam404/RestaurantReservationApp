package com.android.data.repository

import com.android.data.mapper.BannerResponseMapper
import com.android.data.mapper.localBannerResponseMapper
import com.android.data.mapper.bannerToBannerEntityMapper
import com.android.data.source.local.BannerLocalDataSource
import com.android.data.source.remote.api.RestaurantRemoteDataSource
import com.android.data.util.repository.BaseRepository
import com.android.domain.model.Banner
import com.android.domain.repository.BannerRepository

class BannerRepositoryImpl (
    private val remoteDataSource: RestaurantRemoteDataSource,
    private val localDataSource: BannerLocalDataSource
) : BaseRepository(), BannerRepository {
    override suspend fun getBannerList(force: Boolean): Result<List<Banner>> = safeApiCall({
        if (force) {
            remoteDataSource.fetchAdvertisementList().map(BannerResponseMapper)
        } else {
            val localBannerList = localDataSource.fetchBannerList()

            if (localBannerList.isEmpty()) {
                println("FetchType: Remote3")
                remoteDataSource.fetchAdvertisementList().map(BannerResponseMapper)
            } else {
                println("FetchType: Local3")
                localBannerList.map(localBannerResponseMapper)
            }
        }
    })

    override suspend fun insertBannerList(bannerList: List<Banner>): Result<Unit> = safeApiCall({
        localDataSource.insertBannerList(bannerList.map(bannerToBannerEntityMapper))
    })

}