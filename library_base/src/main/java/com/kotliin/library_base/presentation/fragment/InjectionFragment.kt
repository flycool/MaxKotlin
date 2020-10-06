package com.kotliin.library_base.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.kotliin.library_base.BuildConfig
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.kcontext

/**
 * create by max at 2020/9/30 16:18
 *
 */

abstract class InjectionFragment(@LayoutRes contentLayoutId: Int): Fragment(contentLayoutId) ,KodeinAware {

    override val kodeinContext = kcontext<Fragment>(this)

    override val kodein: Kodein by kodein()

    override val kodeinTrigger: KodeinTrigger? by lazy {
        if(BuildConfig.DEBUG) KodeinTrigger() else super.kodeinTrigger
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        kodeinTrigger?.trigger()
    }

}