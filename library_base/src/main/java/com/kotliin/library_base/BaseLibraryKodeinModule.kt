package com.kotliin.library_base

import com.kotliin.library_base.presentation.navigation.NavManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * create by max at 2020/9/30 15:28
 *
 */
 
internal const val MODULE_NAME = "Base"

val baseModule = Kodein.Module("${MODULE_NAME}Module") {

    bind() from singleton { NavManager() }
}