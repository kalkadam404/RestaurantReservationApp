package com.android.data.repository

import com.android.data.mapper.ProductResponseMapper
import com.android.data.mapper.localProductResponseMapper
import com.android.data.mapper.productToProductEntityMapper
import com.android.data.source.local.ProductLocalDataSource
import com.android.data.source.remote.api.RestaurantRemoteDataSource
import com.android.data.util.repository.BaseRepository
import com.android.domain.model.Product
import com.android.domain.repository.ProductRepository

class ProductRepositoryImpl (
    private val remoteDataSource: RestaurantRemoteDataSource,
    private val localDataSource: ProductLocalDataSource
) : BaseRepository(), ProductRepository {
    override suspend fun getProductList(force: Boolean): Result<List<Product>> = safeApiCall({
        if (force) {
            remoteDataSource.fetchProductList().results.map(ProductResponseMapper)
        } else {
            val localProductList = localDataSource.fetchProductList()

            if (localProductList.isEmpty()) {
                println("FetchType: Remote2")
                remoteDataSource.fetchProductList().results.map(ProductResponseMapper)
            } else {
                println("FetchType: Local2")
                localProductList.map(localProductResponseMapper)
            }
        }
    })

    override suspend fun insertProductList(productList: List<Product>): Result<Unit> = safeApiCall({
        localDataSource.insertProductList(productList.map(productToProductEntityMapper))
    })

}