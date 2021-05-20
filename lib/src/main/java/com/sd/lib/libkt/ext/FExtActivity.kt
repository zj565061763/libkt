package com.sd.lib.libkt.ext

import android.app.Activity
import com.sd.lib.libkt.R
import com.sd.lib.libkt.coroutine.scope.FActivityScope

/**
 * 返回Activity对应的[FActivityScope]
 */
fun Activity.fActivityScope(): FActivityScope {
    if (window == null) throw RuntimeException("Activity Window is null")
    val view = window.decorView

    val tag = view.getTag(R.id.libkt_activity_tag_coroutine_scope)
    if (tag is FActivityScope) {
        return tag
    }

    return FActivityScope(this).also {
        view.setTag(R.id.libkt_activity_tag_coroutine_scope, it)
    }
}