package com.logs.utils

import android.util.Log

class StackTraceUtil {

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