package com.sd.lib.libkt.exception

/**
 * 取消异常
 */
class FExceptionCancellation : FException {
    @JvmOverloads
    constructor(message: String?, cause: Throwable? = null) : super(message, cause)
}