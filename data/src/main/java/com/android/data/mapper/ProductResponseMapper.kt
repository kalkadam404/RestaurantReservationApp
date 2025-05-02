package com.android.data.mapper

import com.android.data.model.product.ProductEntity
import com.android.data.model.product.ProductResponse
import com.android.domain.model.Product

val ProductResponseMapper: (ProductResponse) -> Product = { response ->
    Product(
        id = response.id,
        name = response.restaurant_details.name,
        location = response.restaurant_details.city_name,
        menu_category = response.menu_type_details.name,
        ru_name = response.name_ru,
        kz_name = response.name_kz,
        imageUrl = response.image,
        ru_description = response.description_ru,
        kz_description = response.description_kz,
        calories = response.calories,
        proteins = response.proteins,
        fats = response.fats,
        carbohydrates = response.carbohydrates,
        price = response.price,
        available = response.is_available
    )
}

val localProductResponseMapper: (ProductEntity) -> Product = { response ->
    Product(
        id = response.id,
        name = response.restaurant_name,
        location = response.restaurant_city_name,
        menu_category = response.menu_type_name,
        ru_name = response.name_ru,
        kz_name = response.name_kz,
        imageUrl = response.imageUrl,
        ru_description = response.description_ru,
        kz_description = response.description_kz,
        calories = response.calories,
        proteins = response.proteins,
        fats = response.fats,
        carbohydrates = response.carbohydrates,
        price = response.price,
        available = response.is_available
    )
}

val productToProductEntityMapper: (Product) -> ProductEntity = { product ->
    ProductEntity(
        id = product.id,
        restaurant_name =product.name,
        restaurant_city_name = product.location,
        menu_type_name = product.menu_category,
        name_ru = product.ru_name,
        name_kz = product.kz_name,
        imageUrl = product.imageUrl,
        description_ru = product.ru_description,
        description_kz = product.kz_description,
        calories = product.calories,
        proteins = product.proteins,
        fats = product.fats,
        carbohydrates = product.carbohydrates,
        price = product.price,
        is_available = product.available
    )
}