package com.kotliin.maxkotlin.feature_alum.domain.model

import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.kotliin.maxkotlin.feature_alum.data.enum.AlbumDataImageSize

/**
 * create by max at 2020/10/3 15:11
 *
 */

internal data class AlbumDomainModel(
    val name: String,
    val artist: String,
    val images: List<AlbumImageDomainModel>,
    val wiki: AlbumWikiDomainModel? = null,
    val mbId: String? = null
) {
    fun getDefaultImageUrl() =
        images.firstOrNull { it.size == AlbumDomainImageSize.EXTRA_LARGE }?.url
}