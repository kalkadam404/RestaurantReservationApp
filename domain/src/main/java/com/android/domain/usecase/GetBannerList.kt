package com.android.domain.usecase

import com.android.domain.model.Banner
import com.android.domain.repository.BannerRepository
import com.android.domain.util.UseCase


class GetBannerList (
    private val repository: BannerRepository
) : UseCase<List<Banner>, Boolean>() {

    override suspend fun run(params: Boolean): Result<List<Banner>> {
        return repository.getBannerList(params)
    }
}