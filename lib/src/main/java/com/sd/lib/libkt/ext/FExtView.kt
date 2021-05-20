package com.sd.lib.libkt.ext

import android.app.Activity
import android.view.View
import com.sd.lib.libkt.R
import com.sd.lib.libkt.coroutine.scope.FViewScope
import com.sd.lib.libkt.model.FLayoutChangeParams
import com.sd.lib.libkt.utils.LibUtils
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * View是否被添加到UI上面
 */
fun View?.fIsAttached(): Boolean {
    return LibUtils.isAttached(this)
}

/**
 * 将View从父容器移除
 */
fun View?.fRemoveSelf(): Boolean {
    return LibUtils.removeSelf(this)
}

/**
 * View所在的Activity是否结束[Activity.isFinishing]
 */
fun View?.fIsFinishing(): Boolean {
    return LibUtils.isFinishing(this)
}

/**
 * 返回View对应的[FViewScope]
 */
fun View.fViewScope(): FViewScope {
    val tag = getTag(R.id.libkt_view_tag_coroutine_scope)
    if (tag is FViewScope) {
        return tag
    }

    return FViewScope(this).also {
        setTag(R.id.libkt_view_tag_coroutine_scope, it)
    }
}

/**
 * 等待View被添加到UI上面
 */
suspend fun View.fAwaitAttached() = suspendCoroutine<View> { it ->
    if (LibUtils.isAttached(this)) {
        it.resume(this)
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View?) {
                v?.removeOnAttachStateChangeListener(this)
                it.resume(this@fAwaitAttached)
            }

            override fun onViewDetachedFromWindow(v: View?) {
                v?.removeOnAttachStateChangeListener(this)
            }
        })
    }
}

/**
 * 等待[View.OnLayoutChangeListener]触发一次
 */
suspend fun View.fAwaitLayoutChanged() = suspendCoroutine<FLayoutChangeParams> { it ->
    this.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(
            v: View?,
            left: Int,
            top: Int,
            right: Int,
            bottom: Int,
            oldLeft: Int,
            oldTop: Int,
            oldRight: Int,
            oldBottom: Int
        ) {
            removeOnLayoutChangeListener(this)
            it.resume(
                FLayoutChangeParams(
                    view = v!!,
                    left = left,
                    top = top,
                    right = right,
                    bottom = bottom,
                    oldLeft = oldLeft,
                    oldTop = oldTop,
                    oldRight = oldRight,
                    oldBottom = oldBottom,
                )
            )
        }
    })
}