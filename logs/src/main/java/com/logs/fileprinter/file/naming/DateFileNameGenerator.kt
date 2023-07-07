package com.logs.fileprinter.file.naming

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

/**
 * Generate file name according to the timestamp, different dates will lead to different file names.
 */
class DateFileNameGenerator : FileNameGenerator {

    var localDateFormat: ThreadLocal<SimpleDateFormat> =
        object : ThreadLocal<SimpleDateFormat>() {
            override fun initialValue(): SimpleDateFormat {
                return SimpleDateFormat("yyyy-MM-dd", Locale.US)
            }
        }

    override fun isFileNameChangeable(): Boolean {
        return false
    }

    /**
     * Generate a file name which represent a date.
     */
    override fun generateFileName(logLevel: Int, timeStamp: Long): String {
        val simpleDateFormat: SimpleDateFormat = localDateFormat.get() as SimpleDateFormat
        simpleDateFormat.timeZone = TimeZone.getDefault()
        return simpleDateFormat.format(timeStamp)
    }

}