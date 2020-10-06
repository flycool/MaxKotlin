package com.kotliin.maxkotlin.feature_alum.domain.repository

import com.kotliin.maxkotlin.feature_alum.domain.model.AlbumDomainModel


internal interface AlbumRepository {

    suspend fun getAlbumInfo(artistName: String, albumName: String, mbId: String?): AlbumDomainModel?

    suspend fun searchAlbum(phrase: String): List<AlbumDomainModel>
}
