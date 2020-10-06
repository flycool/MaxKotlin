package com.kotliin.library_base.presentation.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.kotliin.library_base.BuildConfig
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.kodein
import org.kodein.di.android.retainedKodein
import org.kodein.di.generic.kcontext

/**
 * create by max at 2020/9/30 16:25
 *
 */

abstract class InjectionActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId), KodeinAware {

    private val parentKodein by kodein()

    override val kodeinContext: KodeinContext<*> = kcontext<AppCompatActivity>(this)

    // Using retainedKodein will not recreate Kodein when the Activity restarts
    override val kodein: Kodein by retainedKodein {
        extend(parentKodein)
    }

    /*
   Dependency resolution for debug builds:
   By defining kodeinTrigger we can eagerly retrieve all dependencies in onCreate method. This allow us to be sure
   that all dependencies have correctly been retrieved (there were no non-declared dependencies and no dependency
   loops)

   Dependency resolution for release builds:
   By not using kodeinTrigger all dependencies will be resolved lazily. This allow to save some resources and speed up
   the application by retrieving dependencies only when they are needed/used.

   More:
   https://github.com/Kodein-Framework/Kodein-DI/blob/master/doc/android.adoc#using-a-trigger
   http://kodein.org/Kodein-DI/?latest/android#_using_a_trigger

    */
    override val kodeinTrigger: KodeinTrigger? by lazy {
        if (BuildConfig.DEBUG) KodeinTrigger() else super.kodeinTrigger
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        kodeinTrigger?.trigger()
    }
}