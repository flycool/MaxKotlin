package com.kotliin.maxkotlin.feature_alum.data.model

import com.kotliin.maxkotlin.feature_alum.data.enum.AlbumDataImageSize
import com.kotliin.maxkotlin.feature_alum.data.enum.toDomainEnum
import com.kotliin.maxkotlin.feature_alum.domain.model.AlbumImageDomainModel
import com.squareup.moshi.Json

/**
 * create by max at 2020/10/3 15:00
 *
 */

internal data class AlbumImageDataModel(
    @field:Json(name = "#text")
    val url: String,
    val size: AlbumDataImageSize
)

internal fun AlbumImageDataModel.toDomainModel() = AlbumImageDomainModel(
    url = this.url,
    size = this.size.toDomainEnum()
)

