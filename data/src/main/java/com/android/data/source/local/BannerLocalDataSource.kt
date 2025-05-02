package com.android.data.source.local

import com.android.data.model.banner.BannerEntity
import com.android.data.source.local.dao.BannerDao

class BannerLocalDataSource (private val dao: BannerDao){

    suspend fun fetchBannerList() = dao.getAll()

    suspend fun insertBannerList(bannerList: List<BannerEntity>) = dao.insertAll(bannerList)
}