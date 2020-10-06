package com.kotliin.library_base.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igorwojda.showcase.library.base.presentation.viewmodel.StateTimeTravelDebugger
import com.kotliin.library_base.BuildConfig
import com.kotliin.library_base.presentation.extension.asLiveData
import kotlin.properties.Delegates

/**
 * create by max at 2020/9/30 15:41
 *
 */
 
abstract class BaseViewModel<ViewState: BaseViewState, ViewAction: BaseAction>(initialState: ViewState): ViewModel() {

    private val stateMutableLiveData = MutableLiveData<ViewState>()
    val stateLiveData = stateMutableLiveData.asLiveData()

    private var stateTimeTravelDebugger: StateTimeTravelDebugger? = null

    init {
        if (BuildConfig.DEBUG) {
            stateTimeTravelDebugger = StateTimeTravelDebugger(this::class.java.simpleName)
        }
    }

    protected var state by Delegates.observable(initialState) { _, old, new ->
        stateMutableLiveData.value = new

        if (new != old) {
            stateTimeTravelDebugger?.apply {
                addStateTransition(old, new)
                logLast()
            }
        }

    }

    fun sendAction(viewAction: ViewAction) {
        stateTimeTravelDebugger?.addAction(viewAction)
        state = onReduceState(viewAction)
    }

    fun loadData() {
        onLoadData()
    }


    protected open fun onLoadData() {}
    protected abstract fun onReduceState(viewAction: ViewAction): ViewState
}

