package com.android.data.mapper

import com.android.data.model.product.ProductTypeEntity
import com.android.data.model.product.ProductTypeResponse
import com.android.domain.model.ProductType

val ProductTypeResponseMapper: (ProductTypeResponse) -> ProductType = { response ->
    ProductType(
        id = response.id,
        title = response.name,
    )
}

val localProductTypeResponseMapper: (ProductTypeEntity) -> ProductType = { response ->
    ProductType(
        id = response.id,
        title = response.name
    )
}

val productTypeToProductTypeEntityMapper: (ProductType) -> ProductTypeEntity = { productType ->
    ProductTypeEntity(
        id = productType.id,
        name =productType.title,
    )
}