package com.sd.lib.libkt.model

class FResult<T> internal constructor(val data: T?, val failure: FFailure?) {
    val isSuccess: Boolean

    init {
        val success = data != null && failure == null
        val failure = data == null && failure != null
        assert(success || failure)
        isSuccess = success
    }

    companion object {
        @JvmStatic
        fun <T> success(data: T): FResult<T> {
            return FResult(data, null)
        }

        @JvmStatic
        @JvmOverloads
        fun <T> failure(exception: Exception, code: Int? = null): FResult<T> {
            return FResult(null, FFailure(exception, code = code))
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