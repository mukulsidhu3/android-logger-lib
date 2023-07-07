package com.logs.fileprinter.file.naming

/**
 * Generate a file name that is changeless.
 */
class ChangelessFileNameGenerator internal constructor(private val fileName: String): FileNameGenerator {

    override fun isFileNameChangeable(): Boolean {
        return false
    }

    override fun generateFileName(logLevel: Int, timeStamp: Long): String {
        return fileName
    }
}