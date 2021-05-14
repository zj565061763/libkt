package com.example.libkt

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sd.lib.libkt.model.FResult
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.libkt", appContext.packageName)
    }

    @Test
    fun testFResult() {
        val result = FResult.success("success")
        assertEquals(true, result.isSuccess)
        assertEquals(false, result.isFailure)
        assertEquals("success", result.data)
    }
}