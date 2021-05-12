package com.example.libkt

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.libkt.databinding.ActivityMainBinding
import com.sd.lib.libkt.ext.fAwaitAttached
import com.sd.lib.libkt.ext.fAwaitLayoutChanged
import com.sd.lib.libkt.ext.fIsAttached
import com.sd.lib.libkt.ext.fObjectId
import com.sd.lib.libkt.model.FResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val success = FResult.success<Activity>(this)
        val failure = FResult.failure<Activity>()
        val view = _binding.tvContent

        Log.i(TAG, "activity:${fObjectId()}")

        GlobalScope.launch(Dispatchers.Main) {
            Log.i(TAG, "fIsAttached:${view.fIsAttached()}")
            view.fAwaitAttached()
            Log.i(TAG, "fIsAttached:${view.fIsAttached()}")
        }

        GlobalScope.launch(Dispatchers.Main) {
            Log.i(TAG, "width:${view.width}")
            view.fAwaitLayoutChanged()
            Log.i(TAG, "width:${view.width}")
        }
    }
}