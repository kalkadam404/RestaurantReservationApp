package com.android.domain.usecase

import com.android.domain.model.Banner
import com.android.domain.repository.BannerRepository
import com.android.domain.util.UseCase


class InsertBannerList(
    private val repository: BannerRepository
) : UseCase<Unit, List<Banner>>() {

    override suspend fun run(params: List<Banner>): Result<Unit> {
        return repository.insertBannerList(params)
    }
}