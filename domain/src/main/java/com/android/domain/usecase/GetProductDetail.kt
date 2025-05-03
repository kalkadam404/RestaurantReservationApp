package com.android.domain.usecase

import com.android.domain.model.Product
import com.android.domain.repository.ProductDetailRepository
import com.android.domain.util.UseCase

class GetProductDetail(
    private val repository: ProductDetailRepository
) : UseCase<Product, GetProductDetail.Params>() {

    data class Params(val productId: Int, val force: Boolean)

    override suspend fun run(params: Params): Result<Product> {
        return repository.getProductDetail(params.productId, params.force)
    }
}
