package com.kotliin.maxkotlin.feature_alum

import com.kotliin.maxkotlin.app.featrue.KodeinModuleProvider
import com.kotliin.maxkotlin.feature_alum.data.dataModule
import com.kotliin.maxkotlin.feature_alum.domain.domainModule
import com.kotliin.maxkotlin.feature_alum.presentation.presentationModule
import org.kodein.di.Kodein

/**
 * create by max at 2020/10/3 15:41
 *
 */

internal const val MODULE_NAME = "Album"

object FeatureKodeinModule : KodeinModuleProvider{
    override val kodeinModule = Kodein.Module("${MODULE_NAME}Module") {
        import(dataModule)
        import(domainModule)
        import(presentationModule)
    }
}