package com.kotliin.maxkotlin.feature_alum.presentation

import androidx.fragment.app.Fragment
import coil.ImageLoader
import com.kotliin.library_base.di.KotlinViewModelProvider
import com.kotliin.maxkotlin.feature_alum.MODULE_NAME
import com.kotliin.maxkotlin.feature_alum.presentation.albumlist.AlbumListViewModel
import com.kotliin.maxkotlin.feature_alum.presentation.albumlist.recyclerview.AlbumAdapter
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${MODULE_NAME}PresentationModule") {

    // AlbumList
    bind<AlbumListViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { AlbumListViewModel(instance(), instance()) }
    }

    bind() from singleton { AlbumAdapter() }

    bind() from singleton { ImageLoader(instance()) }

    // AlbumDetails
//    bind<AlbumDetailViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
//        KotlinViewModelProvider.of(context) { AlbumDetailViewModel(instance(), instance()) }
//    }
}
