package com.kotliin.maxkotlin.feature_alum.data.model

import com.kotliin.maxkotlin.feature_alum.domain.model.AlbumWikiDomainModel


internal data class AlbumWikiDataModel(
    val published: String,
    val summary: String
)

internal fun AlbumWikiDataModel.toDomainModel() = AlbumWikiDomainModel(
    published = this.published,
    summary = this.summary
)
