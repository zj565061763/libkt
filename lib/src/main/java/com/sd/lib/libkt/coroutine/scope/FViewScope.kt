package com.sd.lib.libkt.coroutine.scope

import android.view.View
import com.sd.lib.libkt.utils.LibUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class FViewScope : FCoroutineScope {
    private val _view: View
    private val _mainScope by lazy { FMainScope() }

    constructor(view: View) {
        _view = view
    }

    /**
     * 协程
     */
    override fun launch(
        context: CoroutineContext,
        start: CoroutineStart,
        block: suspend CoroutineScope.() -> Unit
    ): Job? {
        if (!LibUtils.isAttached(_view)) {
            // 如果view没有被添加到UI上面，不处理
            return null
        }

        if (_mainScope.init()) {
            _view.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
                override fun onViewAttachedToWindow(v: View?) {
                }

                override fun onViewDetachedFromWindow(v: View?) {
                    _mainScope.destroy()
                    _view.removeOnAttachStateChangeListener(this)
                }
            })
        }

        return _mainScope.launch(
            context = context,
            start = start,
            block = block
        )
    }
}