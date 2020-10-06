package com.kotliin.maxkotlin.feature_alum.data

import com.kotliin.maxkotlin.feature_alum.MODULE_NAME
import com.kotliin.maxkotlin.feature_alum.data.repository.AlbumRepositoryImpl
import com.kotliin.maxkotlin.feature_alum.data.retorfit.service.AlbumRetrofitService
import com.kotliin.maxkotlin.feature_alum.domain.repository.AlbumRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

/**
 * create by max at 2020/10/3 15:45
 *
 */
 
internal val dataModule = Kodein.Module("${MODULE_NAME}DataModule") {

    bind<AlbumRepository>() with singleton { AlbumRepositoryImpl(instance()) }

    bind() from singleton { instance<Retrofit>().create(AlbumRetrofitService::class.java) }
}