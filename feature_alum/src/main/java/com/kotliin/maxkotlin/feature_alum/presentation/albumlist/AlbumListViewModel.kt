package com.kotliin.maxkotlin.feature_alum.presentation.albumlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotliin.library_base.presentation.navigation.NavManager
import com.kotliin.library_base.presentation.viewmodel.BaseAction
import com.kotliin.library_base.presentation.viewmodel.BaseViewModel
import com.kotliin.library_base.presentation.viewmodel.BaseViewState
import com.kotliin.maxkotlin.feature_alum.domain.model.AlbumDomainModel
import com.kotliin.maxkotlin.feature_alum.domain.usecase.GetAlbumListUseCase
import kotlinx.coroutines.launch

/**
 * create by max at 2020/10/4 15:56
 *
 */

internal class AlbumListViewModel(
    private val navManager: NavManager,
    private val getAlbumListUseCase: GetAlbumListUseCase
): BaseViewModel<AlbumListViewModel.ViewState, AlbumListViewModel.Action>(ViewState()) {

    override fun onLoadData() {
        getAlbumList()
    }

    override fun onReduceState(viewAction: Action): ViewState = when(viewAction) {
        is Action.AlbumListLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            albums = viewAction.albums
        )
        is Action.AlbumListLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            albums = listOf()
        )
    }

    private fun getAlbumList() {
        viewModelScope.launch {
            getAlbumListUseCase.execute().also { result ->
                val action = when (result) {
                    is GetAlbumListUseCase.Result.Success ->
                        if (result.data.isEmpty()) {
                            Action.AlbumListLoadingFailure
                        } else {
                            Action.AlbumListLoadingSuccess(result.data)
                        }
                    is GetAlbumListUseCase.Result.Error -> {
                        Action.AlbumListLoadingFailure

                    }
                }

                sendAction(action)
            }
        }
    }

    fun navigateToAlbumDetails(artistName: String, albumName: String, mbId: String?) {

    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val albums: List<AlbumDomainModel> = listOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class AlbumListLoadingSuccess(val albums: List<AlbumDomainModel>): Action()
        object AlbumListLoadingFailure: Action()
    }


}