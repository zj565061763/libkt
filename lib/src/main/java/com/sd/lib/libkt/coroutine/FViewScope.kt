package com.sd.lib.libkt.coroutine

import android.view.View
import com.sd.lib.libkt.utils.LibUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class FViewScope {
    val _view: View
    private val _mainScope by lazy { FMainScope() }

    constructor(view: View) {
        _view = view
        view.addOnAttachStateChangeListener(_onAttachStateChangeListener)
        if (LibUtils.isAttached(view)) {
            _mainScope.init()
        }
    }

    /**
     * 协程
     */
    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job? {
        return _mainScope.launch(
            context = context,
            start = start,
            block = block
        )
    }

    private val _onAttachStateChangeListener = object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(v: View?) {
            _mainScope.init()
        }

        override fun onViewDetachedFromWindow(v: View?) {
            _mainScope.destroy()
        }
    }
}