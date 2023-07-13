package com.logs.utils

class StackTraceUtil {

    fun getCroppedRealStackTrack(
        stackTrace: Array<StackTraceElement>,
        stackTraceOrigin: String,
        maxDepth: Int
    ): Array<StackTraceElement?> {
        return getCropStackTrace(
            getRealStackTrack(
                stackTrace,
                stackTraceOrigin
            ), maxDepth
        )
    }

    fun getRealStackTrack(
        stackTrace: Array<StackTraceElement>,
        stackTraceOrigin: String
    ): Array<StackTraceElement?> {
        var ignoreDepth = 0
        val allDepth = stackTrace.size
        var className: String
        for (i in allDepth - 1 downTo 0) {
            className = stackTrace[i].className
            if (className.startsWith("") || stackTraceOrigin != null && className.startsWith(
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