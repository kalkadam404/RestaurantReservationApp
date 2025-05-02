package com.android.data.repository

import com.android.data.mapper.ProductTypeResponseMapper
import com.android.data.mapper.localProductTypeResponseMapper
import com.android.data.mapper.productTypeToProductTypeEntityMapper
import com.android.data.source.local.ProductTypeLocalDataSource
import com.android.data.source.remote.api.RestaurantRemoteDataSource
import com.android.data.util.repository.BaseRepository
import com.android.domain.model.ProductType
import com.android.domain.repository.ProductTypeRepository

class ProductTypeRepositoryImpl (
    private val remoteDataSource: RestaurantRemoteDataSource,
    private val localDataSource: ProductTypeLocalDataSource
) : BaseRepository(), ProductTypeRepository {
    override suspend fun getProductTypeList(force: Boolean): Result<List<ProductType>> = safeApiCall({
        if (force) {
            remoteDataSource.fetchProductTypeList().results.map(ProductTypeResponseMapper)
        } else {
            val localProductTypeList = localDataSource.fetchProductTypeList()

            if (localProductTypeList.isEmpty()) {
                println("FetchType: Remote2")
                remoteDataSource.fetchProductTypeList().results.map(ProductTypeResponseMapper)
            } else {
                println("FetchType: Local2")
                localProductTypeList.map(localProductTypeResponseMapper)
            }
        }
    })

    override suspend fun insertProductTypeList(productTypeList: List<ProductType>): Result<Unit> = safeApiCall({
        localDataSource.insertProductTypeList(productTypeList.map(productTypeToProductTypeEntityMapper))
    })

}