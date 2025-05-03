package com.android.domain.repository

import com.android.domain.model.Product

interface ProductDetailRepository {
    suspend fun getProductDetail(productId: Int, force: Boolean): Result<Product>
    suspend fun insertProductDetail(product: Product): Result<Unit>
}