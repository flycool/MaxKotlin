package com.kotliin.library_base.presentation.navigation

import androidx.navigation.NavDirections

/**
 * create by max at 2020/9/30 15:31
 *
 */
 
class NavManager {
    private var navEventListener: ((navDirections: NavDirections)->Unit)? = null

    fun navigate(navDirections: NavDirections) {
        navEventListener?.invoke(navDirections)
    }

    fun setOnNavEvent(navEventListener: (navDirections: NavDirections) -> Unit) {
        this.navEventListener = navEventListener
    }
}