package com.kotliin.maxkotlin.feature_alum.data.retorfit.service

import com.kotliin.maxkotlin.feature_alum.data.retorfit.response.GetAlbumInfoResponse
import com.kotliin.maxkotlin.feature_alum.data.retorfit.response.SearchAlbumResponse
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * create by max at 2020/10/3 15:21
 *
 */

internal interface AlbumRetrofitService {
    @POST("./?method=album.search")
    suspend fun searchAlbumAsync(
        @Query("album")
        phrase: String,
        @Query("limit")
        limit: Int = 60
    ): SearchAlbumResponse

    @POST("./?method=album.getInfo")
    suspend fun getAlbumInfoAsync(
        @Query("artist") artistName: String,
        @Query("album") albumName: String,
        @Query("mbid") mbId: String?
    ): GetAlbumInfoResponse?
}