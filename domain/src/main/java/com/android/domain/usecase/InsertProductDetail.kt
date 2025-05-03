package com.android.domain.usecase

import com.android.domain.model.Product
import com.android.domain.repository.ProductDetailRepository
import com.android.domain.util.UseCase

class InsertProductDetail(
    private val repository: ProductDetailRepository
) : UseCase<Unit, InsertProductDetail.Params>() {

    // Params will hold the single Product that needs to be inserted
    data class Params(val product: Product)

    override suspend fun run(params: Params): Result<Unit> {
        return repository.insertProductDetail(params.product)
    }
}
