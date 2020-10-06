package com.kotliin.maxkotlin.feature_alum.data.model

import com.kotliin.maxkotlin.feature_alum.data.enum.AlbumDataImageSize
import com.kotliin.maxkotlin.feature_alum.domain.model.AlbumDomainModel
import com.squareup.moshi.Json

/**
 * create by max at 2020/10/3 14:57
 *
 */

internal data class AlbumDataModel(
    @field:Json(name = "mbid")
    val mbId: String?,
    val name: String,
    val artist: String,
    val wiki: AlbumWikiDataModel?,
    @field:Json(name = "image")
    val images: List<AlbumImageDataModel>?
)

internal fun AlbumDataModel.toDomainModel(): AlbumDomainModel {
    val images = this.images
        ?.filterNot { it.size == AlbumDataImageSize.UNKNOW || it.url.isBlank() }
        ?.map { it.toDomainModel() }

    return AlbumDomainModel(
        mbId = this.mbId,
        name = this.name,
        artist = this.artist,
        images = images ?: listOf(),
        wiki = this.wiki?.toDomainModel()
    )
}