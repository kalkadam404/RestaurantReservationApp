package com.android.data.repository

import com.android.data.mapper.ProductDetailResponseMapper
import com.android.data.mapper.localProductDetailResponseMapper
import com.android.data.mapper.productDetailToProductDetailEntityMapper
import com.android.data.source.local.ProductLocalDataSource
import com.android.data.source.remote.api.RestaurantRemoteDataSource
import com.android.data.util.repository.BaseRepository
import com.android.domain.model.Product
import com.android.domain.repository.ProductDetailRepository

class ProductDetailRepositoryImpl(
    private val remoteDataSource: RestaurantRemoteDataSource,
    private val localDataSource: ProductLocalDataSource
) : BaseRepository(), ProductDetailRepository {

    override suspend fun getProductDetail(id: Int, force: Boolean): Result<Product> = safeApiCall({
        if (force) {
            // Fetch from remote and map it to Product using ProductDetailResponseMapper
            ProductDetailResponseMapper(remoteDataSource.fetchProductDetail(id))
        } else {
            // Fetch from local DB
            val localProductDetail = localDataSource.fetchProductDetail(id)

            run {
                println("FetchType: Local")
                // Map the local entity to Product
                localProductDetailResponseMapper(localProductDetail)
            }
        }
    })

    override suspend fun insertProductDetail(product: Product): Result<Unit> = safeApiCall({
        localDataSource.insertProductDetail(productDetailToProductDetailEntityMapper(product))
    })
}
