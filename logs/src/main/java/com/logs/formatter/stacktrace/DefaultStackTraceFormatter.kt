package com.logs.formatter.stacktrace

import com.logs.Constants

class DefaultStackTraceFormatter: StackTraceFormatter {

    override fun format(data: Array<StackTraceElement?>): String {
        val sb = StringBuilder(256)
        return if (data.isEmpty()) {
           return Constants.EMPTY_STRING
        } else if (data.size == 1) {
            "\t─ " + data[0].toString()
        } else {
            var i = 0
            val n: Int = data.size
            while (i < n) {
                if (i != n - 1) {
                    sb.append("\t├ ")
                    sb.append(data[i].toString())
                  //  sb.append(SystemCompat.lineSeparator)
                } else {
                    sb.append("\t└ ")
                    sb.append(data[i].toString())
                }
                i++
            }
            sb.toString()
        }
    }
}