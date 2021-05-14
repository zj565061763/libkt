package com.example.libkt

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sd.lib.libkt.model.FResult
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun testResultSuccess() {
        val result = FResult.success("success")
        assertEquals(true, result.isSuccess)
        assertEquals(false, result.isFailure)
        assertEquals("success", result.data)
        assertEquals(null, result.failure)
    }

    @Test
    fun testResultFailure() {
        val result = FResult.failure<String>("failure")
        assertEquals(true, result.isFailure)
        assertEquals(false, result.isSuccess)
        assertEquals(null, result.data)
        assertEquals("failure", result.failure!!.toString())

        val resultObject = FResult.failure<Objects>(result)
        assertEquals(result.failure, resultObject.failure)
        assertEquals(result, resultObject)
    }
}