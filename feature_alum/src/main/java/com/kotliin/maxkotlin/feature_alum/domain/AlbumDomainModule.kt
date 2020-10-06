package com.kotliin.maxkotlin.feature_alum.domain

import com.kotliin.maxkotlin.feature_alum.MODULE_NAME
import com.kotliin.maxkotlin.feature_alum.domain.usecase.GetAlbumListUseCase
import com.kotliin.maxkotlin.feature_alum.domain.usecase.GetAlbumUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${MODULE_NAME}DomainModule") {

    bind() from singleton { GetAlbumListUseCase(instance()) }

    bind() from singleton { GetAlbumUseCase(instance()) }
}
