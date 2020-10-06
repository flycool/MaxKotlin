package com.kotliin.maxkotlin.feature_alum.data.enum

import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.squareup.moshi.Json

/**
 * create by max at 2020/10/3 15:01
 *
 */

internal enum class AlbumDataImageSize {
    @field:Json(name = "medium")
    MEDIUM,
    @field:Json(name = "small")
    SMALL,
    @field:Json(name = "large")
    LARGE,
    @field:Json(name = "extralarge")
    EXTRA_LARGE,
    @field:Json(name = "mega")
    MEGA,
    @field:Json(name = "")
    UNKNOW
}

internal fun AlbumDataImageSize.toDomainEnum() = AlbumDomainImageSize.valueOf(this.name)