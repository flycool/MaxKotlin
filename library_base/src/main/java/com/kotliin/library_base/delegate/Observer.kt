package com.kotliin.library_base.delegate

import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * create by max at 2020/9/30 19:13
 *
 */

inline fun <T> observer(
    initialValue: T,
    crossinline onChange: (newValue: T) -> Unit
): ReadWriteProperty<Any?, T> = object : ObservableProperty<T>(initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
        onChange(newValue)
    }
}

