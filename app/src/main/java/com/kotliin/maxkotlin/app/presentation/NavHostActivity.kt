package com.kotliin.maxkotlin.app.presentation

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kotliin.library_base.presentation.activity.BaseActivity
import com.kotliin.library_base.presentation.navigation.NavManager
import com.kotliin.maxkotlin.R
import kotlinx.android.synthetic.main.activity_nav_host.*
import org.kodein.di.generic.instance

/**
 * create by max at 2020/10/1 16:57
 *
 */

class NavHostActivity: BaseActivity(R.layout.activity_nav_host) {

    private val navController get() = navHostFragment.findNavController()

    private val navManager: NavManager by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
        initNavManager()
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            navController.navigate(it)
        }
    }

    private fun initBottomNavigation() {
        bottomNav.setupWithNavController(navController)
    }
}