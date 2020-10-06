package com.kotliin.maxkotlin.app.featrue

import com.kotliin.maxkotlin.BuildConfig

/**
 * create by max at 2020/10/5 15:04
 *
 */

// Dynamic Feature modules require reversed dependency (dynamic feature module depends on app module)
// This means we have to use reflection to access module content
// See: https://medium.com/mindorks/dynamic-feature-modules-the-future-4bee124c0f1
object FeatureManager {

    private const val featurePackagefix = "com.kotliin.maxkotlin"

    val kodeinModules = BuildConfig.FEATURE_MODULE_NAMES
        .map { "$featurePackagefix.$it.FeatureKodeinModule" }
        .map {
            try {
              Class.forName(it).kotlin.objectInstance as KodeinModuleProvider
            } catch(e: ClassNotFoundException) {
                throw ClassNotFoundException("Kodein module class not found $it")
            }
        }
        .map { it.kodeinModule }


}