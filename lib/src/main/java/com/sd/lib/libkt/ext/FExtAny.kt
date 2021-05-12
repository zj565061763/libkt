package com.sd.lib.libkt.ext

/**
 * 对象Id
 */
fun Any.fObjectId(): String {
    val hashCode = System.identityHashCode(this)
    val hashCodeString = Integer.toHexString(hashCode)
    return "${javaClass.name}@${hashCodeString}"
}