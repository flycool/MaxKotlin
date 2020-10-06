package com.kotliin.maxkotlin.feature_alum.data.repository

import com.kotliin.maxkotlin.feature_alum.data.model.toDomainModel
import com.kotliin.maxkotlin.feature_alum.data.retorfit.service.AlbumRetrofitService
import com.kotliin.maxkotlin.feature_alum.domain.model.AlbumDomainModel
import com.kotliin.maxkotlin.feature_alum.domain.repository.AlbumRepository

/**
 * create by max at 2020/10/3 15:34
 *
 */

internal class AlbumRepositoryImpl(
    private val albumRetrofitService: AlbumRetrofitService
) : AlbumRepository {
    override suspend fun getAlbumInfo(
        artistName: String,
        albumName: String,
        mbId: String?
    ): AlbumDomainModel? =
        albumRetrofitService.getAlbumInfoAsync(artistName, albumName, mbId)
            ?.album
            ?.toDomainModel()

    override suspend fun searchAlbum(phrase: String): List<AlbumDomainModel> =
        albumRetrofitService.searchAlbumAsync(phrase)
            .results
            .albumMatches
            .album
            .map { it.toDomainModel() }

}