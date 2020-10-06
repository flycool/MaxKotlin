package com.kotliin.maxkotlin.app

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.kotliin.library_base.baseModule
import com.kotliin.maxkotlin.BuildConfig
import com.kotliin.maxkotlin.app.featrue.FeatureManager
import com.kotliin.maxkotlin.appModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import timber.log.Timber

/**
 * create by max at 2020/9/30 19:26
 *
 */

class MaxKotlinApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MaxKotlinApplication))
        import(baseModule)
        import(appModule)
        importAll(FeatureManager.kodeinModules)
    }

    private lateinit var  context: Context

    override fun onCreate() {
        super.onCreate()

        context = this

        initTimber()
        initStetho()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}