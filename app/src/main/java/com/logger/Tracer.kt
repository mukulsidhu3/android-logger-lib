package com.logger

import android.util.Log
import com.logs.formatter.stacktrace.DefaultStackTraceFormatter
import com.logs.utils.StackTraceUtil

class Tracer {

    companion object{
        fun stackTraceFormat(){

            val stackTrace = StackTraceUtil().getCroppedRealStackTrace(Throwable().stackTrace,"kk",5)
            val string = DefaultStackTraceFormatter().format(stackTrace)

            Log.d("StackTraceLog", string)
        }
    }
}