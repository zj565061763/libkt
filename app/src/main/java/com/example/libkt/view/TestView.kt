package com.example.libkt.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import com.sd.lib.libkt.ext.fViewScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestView : FrameLayout {
    val TAG = TestView::class.java.simpleName

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        assert(fViewScope() == fViewScope())
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        fViewScope().launch {
            while (true) {
                Log.i(TAG, "launch run view")
                delay(1000)
            }
        }
    }
}