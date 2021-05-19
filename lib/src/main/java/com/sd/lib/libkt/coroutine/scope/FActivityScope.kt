package com.sd.lib.libkt.coroutine.scope

import android.app.Activity
import android.app.Application
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class FActivityScope : FCoroutineScope {
    private lateinit var _activity: Activity
    private val _mainScope by lazy { FMainScope() }

    constructor(activity: Activity) : super() {
        _activity = activity
    }

    override fun launch(
        context: CoroutineContext,
        start: CoroutineStart,
        block: suspend CoroutineScope.() -> Unit
    ): Job? {
        _activityLifecycleCallbacks
        return _mainScope.launch(
            context = context,
            start = start,
            block = block
        )
    }

    private val _activityLifecycleCallbacks by lazy {
        val callback = object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                if (_activity == activity) {
                    _mainScope.destroy()
                    activity.application.unregisterActivityLifecycleCallbacks(this)
                }
            }
        }
        callback.also {
            val activity = _activity
            if (!activity.isFinishing) {
                activity.application.registerActivityLifecycleCallbacks(it)
                _mainScope.init()
            }
        }
    }
}