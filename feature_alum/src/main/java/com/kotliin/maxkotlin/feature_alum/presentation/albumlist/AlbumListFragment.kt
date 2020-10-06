package com.kotliin.maxkotlin.feature_alum.presentation.albumlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.kotliin.library_base.presentation.extension.observe
import com.kotliin.library_base.presentation.fragment.InjectionFragment
import com.kotliin.maxkotlin.feature_alum.presentation.albumlist.recyclerview.AlbumAdapter
import com.kotliin.maxkotlin.feature_alum.presentation.albumlist.recyclerview.GridAutofitLayoutManager
import com.kotlin.maxkotlin.feature_alum.R
import com.pawegio.kandroid.visible
import kotlinx.android.synthetic.main.fragment_album_list.*
import org.kodein.di.generic.instance

/**
 * create by max at 2020/10/1 19:22
 *
 */

class AlbumListFragment : InjectionFragment(R.layout.fragment_album_list) {

    private val viewModel: AlbumListViewModel by instance()

    private val albumAdapter: AlbumAdapter by instance()

    private val stateObserver = Observer<AlbumListViewModel.ViewState> {
        albumAdapter.albums = it.albums
        progressBar.visible = it.isLoading
        errorAnimation.visible = it.isError
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()

        albumAdapter.setOnDebouncedClickListener {
            viewModel.navigateToAlbumDetails(it.artist, it.name, it.mbId)
        }

        recyclerView.apply {
            setHasFixedSize(true)
            val columnWidth = context.resources.getDimension(R.dimen.image_size).toInt()
            layoutManager = GridAutofitLayoutManager(context, columnWidth)
            adapter = albumAdapter
        }

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()
    }
}