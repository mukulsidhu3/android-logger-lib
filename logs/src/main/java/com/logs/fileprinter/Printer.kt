package com.logs.fileprinter

/**
 * This is interface is used for print log.
 */
interface Printer {

    fun println(logLevel: String, tag: String, msg: String)
}