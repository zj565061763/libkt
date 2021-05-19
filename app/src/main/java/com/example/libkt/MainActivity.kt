package com.example.libkt

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.libkt.databinding.ActivityMainBinding
import com.sd.lib.libkt.coroutine.scope.FActivityScope
import com.sd.lib.libkt.ext.fObjectId
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName

    private lateinit var _binding: ActivityMainBinding

    /** Activity协程作用域 */
    private val _activityScope = FActivityScope(this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _activityScope.launch {
            while (true) {
                Log.i(TAG, "launch run")
                delay(1000)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "activity fObjectId:${fObjectId()}")
    }
}