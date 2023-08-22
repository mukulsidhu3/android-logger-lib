package com.logs.utils

import android.util.Log

/**
 * Utility related with stack trace.
 */
class StackTraceUtil {

    /**
     * Get the real stack trace and then crop it with a max depth.
     */
    fun getCroppedRealStackTrace(
        stackTrace: Array<StackTraceElement>,
        stackTraceOrigin: String,
        maxDepth: Int
    ): Array<StackTraceElement?> {
        return getCropStackTrace(
            getRealStackTrace(
                stackTrace,
                stackTraceOrigin
            ), maxDepth
        )
    }


    /**
     * Get the real stack trace, all elements that come from XLog library would be dropped.
     */
    fun getRealStackTrace(
        stackTrace: Array<StackTraceElement>,
        stackTraceOrigin: String
    ): Array<StackTraceElement?> {
        var ignoreDepth = 0
        val allDepth = stackTrace.size
        var className: String
        for (i in allDepth - 1 downTo 0) {
            className = stackTrace[i].className
            Log.d("ClassName", className)
            if (className.startsWith("com.logs.Logger") || stackTraceOrigin != null && className.startsWith(
                    stackTraceOrigin
                )
            ) {
                ignoreDepth = i + 1
                break
            }
        }
        val realDepth = allDepth - ignoreDepth
        val realStack = arrayOfNulls<StackTraceElement>(realDepth)
        System.arraycopy(stackTrace, ignoreDepth, realStack, 0, realDepth)
        return realStack
    }


    /**
     * Crop the stack trace with a max depth.
     */
    private fun getCropStackTrace(
        callStack: Array<StackTraceElement?>,
        maxDepth: Int
    ): Array<StackTraceElement?> {
        var realDepth = callStack.size
        if (maxDepth > 0) {
            realDepth = Math.min(maxDepth, realDepth)
        }
        val realStack = arrayOfNulls<StackTraceElement>(realDepth)
        System.arraycopy(callStack, 0, realStack, 0, realDepth)
        return realStack
    }
}