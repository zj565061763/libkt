package com.sd.lib.libkt.model

class FResult<T> internal constructor(val data: T?, val failure: FFailure?) {
    val isSuccess: Boolean get() = data != null && failure == null
    val isFailure: Boolean get() = data == null && failure != null

    init {
        assert(isSuccess || isFailure)
    }

    companion object {
        @JvmStatic
        fun <T> success(data: T): FResult<T> {
            return FResult(data, null)
        }

        @JvmStatic
        @JvmOverloads
        fun <T> failure(
            code: Int = -1,
            message: String? = "",
            exception: Exception? = null
        ): FResult<T> {
            return FResult(null, FFailure(code = code, message = message, exception = exception))
        }
    }
}

class FFailure(
    val code: Int = -1,
    val message: String? = "",
    val exception: Exception? = null
)