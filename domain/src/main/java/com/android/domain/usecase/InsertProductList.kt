package com.android.domain.usecase

import com.android.domain.model.Product
import com.android.domain.repository.ProductRepository
import com.android.domain.util.UseCase


class InsertProductList(
    private val repository: ProductRepository
) : UseCase<Unit, List<Product>>() {

    override suspend fun run(params: List<Product>): Result<Unit> {
        return repository.insertProductList(params)
    }
}