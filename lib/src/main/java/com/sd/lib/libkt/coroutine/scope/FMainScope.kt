package com.sd.lib.libkt.coroutine.scope

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FMainScope : FCoroutineScope() {
    @Volatile
    private var _coroutineScope: CoroutineScope? = null

    /**
     * 是否已经初始化
     */
    val isInitialized: Boolean get() = _coroutineScope != null

    /**
     * 初始化
     */
    @Synchronized
    fun init() {
        if (_coroutineScope == null) {
            _coroutineScope = MainScope()
        }
    }

    /**
     * 协程
     */
    override fun launch(
        context: CoroutineContext,
        start: CoroutineStart,
        block: suspend CoroutineScope.() -> Unit
    ): Job? {
        val scope = _coroutineScope ?: return null
        return scope.launch(
            context = context,
            start = start,
            block = block
        )
    }

    /**
     * 销毁
     */
    @Synchronized
    fun destroy() {
        _coroutineScope?.cancel()
        _coroutineScope = null
    }
}