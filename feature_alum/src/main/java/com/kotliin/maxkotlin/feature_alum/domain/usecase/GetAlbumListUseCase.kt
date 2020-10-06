package com.kotliin.maxkotlin.feature_alum.domain.usecase

import androidx.lifecycle.LiveData
import com.kotliin.maxkotlin.feature_alum.domain.model.AlbumDomainModel
import com.kotliin.maxkotlin.feature_alum.domain.repository.AlbumRepository
import com.kotliin.maxkotlin.feature_alum.netresource.NetworkBoundResourceLivedata
import com.kotliin.maxkotlin.feature_alum.netresource.Resource
import java.io.IOException

/**
 * create by max at 2020/10/3 15:51
 *
 */

internal class GetAlbumListUseCase(
    private val albumRepository: AlbumRepository
) {

    sealed class  Result {
        data class Success(val data: List<AlbumDomainModel>) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun execute(): Result {
        // Due to API limitations we have to perform search with custom phrase to get albums
        val phrase = "sd"

        return try {
            Result.Success(albumRepository.searchAlbum(phrase)
                .filter { it.getDefaultImageUrl() != null }
            )
        } catch (e: IOException) {
            Result.Error(e)
        }
    }

   /* suspend fun searchAlbumLiveData(phrase: String): LiveData<Resource<List<AlbumDomainModel>>> =
        object: NetworkBoundResourceLivedata<List<AlbumDomainModel>>() {
            override suspend fun query(): List<AlbumDomainModel> = listOf()

            override fun queryObservable(): LiveData<List<AlbumDomainModel>> {

            }

            override suspend fun fetch(): List<AlbumDomainModel> {
                return albumRepository.searchAlbum(phrase)
                    .filter { it.getDefaultImageUrl() != null }
            }

            override suspend fun saveFetchResult(data: List<AlbumDomainModel>) {

            }

        }.asLiveData()*/
}