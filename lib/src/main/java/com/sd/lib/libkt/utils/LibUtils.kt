package com.sd.lib.libkt.utils

import android.os.Build
import android.view.View

internal object LibUtils {
    fun isAttached(view: View): Boolean {
        return if (Build.VERSION.SDK_INT >= 19)
            view.isAttachedToWindow
        else
            view.windowToken != null
    }
}