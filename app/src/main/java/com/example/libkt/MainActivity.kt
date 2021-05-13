package com.example.libkt

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.libkt.databinding.ActivityMainBinding
import com.sd.lib.libkt.exception.FException
import com.sd.lib.libkt.ext.fObjectId
import com.sd.lib.libkt.model.FResult

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val resultString = FResult.failure<String>("failure string")
        val resultInt = FResult.failure<Int>(resultString)
        Log.i(TAG, "resultInt isSuccess:${resultInt.isSuccess} failure:${resultInt.failure}")

        val resultSuccess = FResult.success<Activity>(this)
        val resultFailure = FResult.failure<Activity>(FException("failure activity"))
        Log.i(TAG, "result isSuccess:${resultSuccess.isSuccess} data:${resultSuccess.data!!}")
        Log.i(TAG, "result isSuccess:${resultFailure.isSuccess} failure:${resultFailure.failure!!}")

        Log.i(TAG, "activity:${fObjectId()}")
    }
}