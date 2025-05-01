package com.android.domain.repository

import com.android.domain.model.Banner

interface BannerRepository {
    suspend fun getBannerList(force: Boolean): Result<List<Banner>>
    suspend fun insertBannerList(bannerList: List<Banner>): Result<Unit>
}