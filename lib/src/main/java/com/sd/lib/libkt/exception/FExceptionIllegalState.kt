package com.sd.lib.libkt.exception

/**
 * 不合法的逻辑状态，通常需要UI提示
 */
open class FExceptionIllegalState : FException {
    @JvmOverloads
    constructor(message: String?, cause: Throwable? = null) : super(message, cause)
}