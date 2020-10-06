package com.kotliin.library_base.presentation.extension

import android.os.Bundle
import androidx.core.os.bundleOf

/**
 * create by max at 2020/9/30 18:47
 *
 */

fun Bundle.putAny(key: String, value: Any?) {
    putAll(bundleOf(key to value))
}