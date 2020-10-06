package com.kotliin.maxkotlin.feature_alum.domain.model

import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.kotliin.maxkotlin.feature_alum.data.enum.AlbumDataImageSize


/**
 * create by max at 2020/10/3 15:12
 *
 */
 
internal data class AlbumImageDomainModel(
    val url: String,
    val size: AlbumDomainImageSize
)