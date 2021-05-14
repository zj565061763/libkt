package com.sd.lib.libkt.model

import com.sd.lib.libkt.exception.FException

class FResult<T> internal constructor(val data: T?, val failure: FFailure?) {
    val isSuccess: Boolean
    val isFailure: Boolean

    init {
        val success = data != null && failure == null
        val failure = data == null && failure != null
        assert(success || failure)

        isSuccess = success
        isFailure = failure
    }

    companion object {
        @JvmStatic
        fun <T> success(data: T): FResult<T> {
            return FResult(data, null)
        }

        @JvmStatic
        @JvmOverloads
        fun <T> failure(message: String? = "", code: Int? = null): FResult<T> {
            return failure(FException(message = message), code)
        }

        @JvmStatic
        @JvmOverloads
        fun <T> failure(result: FResult<*>): FResult<T> {
            assert(!result.isSuccess)
            val fail = result.failure!!
            return failure(fail.exception, fail.code)
        }

        @JvmStatic
        @JvmOverloads
        fun <T> failure(exception: Exception?, code: Int? = null): FResult<T> {
            return FResult(null, FFailure(exception ?: FException("unknown"), code = code))
        }
    }
}

class FFailure(
    val exception: Exception,
    val code: Int? = null,
) {
    override fun toString(): String {
        val codeString = if (code != null) " ${code} " else ""
        return "${exception}${codeString}"
    }
}