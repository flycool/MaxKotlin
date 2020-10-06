package com.kotliin.maxkotlin.feature_alum.data.model

import com.squareup.moshi.Json

/**
 * create by max at 2020/10/3 15:23
 *
 */

internal data class AlbumSearchResultDataModel(
    @field:Json(name = "albummatches") val albumMatches: AlbumListDataModel
)