package com.sd.lib.libkt.utils

import android.os.Build
import android.view.View
import android.view.ViewGroup

internal object LibUtils {
    fun isAttached(view: View?): Boolean {
        if (view == null) return false

        return if (Build.VERSION.SDK_INT >= 19)
            view.isAttachedToWindow
        else
            view.windowToken != null
    }

    fun removeSelf(view: View?): Boolean {
        if (view == null) return false
        val viewParent = view.parent ?: return false
        if (viewParent !is ViewGroup) return false

        try {
            viewParent.removeView(view)
        } catch (e: Exception) {
        }
        return true
    }
}