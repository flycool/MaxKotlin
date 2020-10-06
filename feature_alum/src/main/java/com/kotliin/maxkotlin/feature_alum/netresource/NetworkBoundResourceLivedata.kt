package com.kotliin.maxkotlin.feature_alum.netresource

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.flow.*

/**
 * create by max at 2020/10/5 21:57
 *
 */

abstract class NetworkBoundResourceLivedata<T> {

    fun asLiveData() = liveData<Resource<T>> {
        emit(Resource.Loading(null))

        if (shouldFetch(query())) {
            val disposable = emitSource(queryObservable().map { Resource.Loading(it) })

            try {
                val fetchedData = fetch()
                // Stop the previous emission to avoid dispatching the saveCallResult as `Resource.Loading`.
                disposable.dispose()
                saveFetchResult(fetchedData)
                // Re-establish the emission as `Resource.Success`.
                emitSource(queryObservable().map { Resource.Success(it) })
            } catch (e: Exception) {
                onFetchFailed(e)
                emitSource(queryObservable().map { Resource.Error(e.toString(), it) })
            }
        } else {
            emitSource(queryObservable().map { Resource.Success(it) })
        }
    }

    abstract suspend fun query(): T
    abstract fun queryObservable(): LiveData<T>
    abstract suspend fun fetch(): T
    abstract suspend fun saveFetchResult(data: T)
    open fun onFetchFailed(exception: Exception) = Unit
    open fun shouldFetch(data: T) = true
}