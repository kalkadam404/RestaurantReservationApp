package com.android.data.mapper

import com.android.data.model.banner.BannerEntity
import com.android.data.model.banner.BannerResponse
import com.android.domain.model.Banner

val BannerResponseMapper: (BannerResponse) -> Banner = { response ->
    Banner(
        id = response.id,
        name = response.title,
        subTitle = response.subtitle,
        description = response.content,
        imageUrl = response.image,
        position = response.position,
        color = response.color_scheme
    )
}

val localBannerResponseMapper: (BannerEntity) -> Banner = { response ->
    Banner(
        id = response.id,
        name = response.banner_name,
        subTitle = response.subtitle,
        description = response.description,
        imageUrl = response.imageUrl,
        position = response.position,
        color = response.color
    )
}

val bannerToBannerEntityMapper: (Banner) -> BannerEntity = { banner ->
    BannerEntity(
        id = banner.id,
        banner_name = banner.name,
        subtitle = banner.subTitle,
        description = banner.description,
        imageUrl = banner.imageUrl,
        position = banner.position,
        color = banner.color
    )
}