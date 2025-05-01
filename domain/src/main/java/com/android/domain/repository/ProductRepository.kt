package com.android.domain.repository
import com.android.domain.model.Product

interface ProductRepository {
    suspend fun getProductList(force: Boolean): Result<List<Product>>
    suspend fun insertProductList(productList: List<Product>): Result<Unit>
}