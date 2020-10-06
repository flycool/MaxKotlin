package com.kotliin.library_base.presentation.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * create by max at 2020/9/30 15:56
 *
 */
 
fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>