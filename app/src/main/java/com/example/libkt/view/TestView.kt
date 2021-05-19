package com.example.libkt.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import com.sd.lib.libkt.coroutine.scope.FViewScope
import kotlinx.coroutines.delay

class TestView : FrameLayout {
    val TAG = TestView::class.java.simpleName

    /** View协程作用域 */
    private val _viewScope = FViewScope(this)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        _viewScope.launch {
            while (true) {
                Log.i(TAG, "launch run view")
                delay(1000)
            }
        }
    }
}