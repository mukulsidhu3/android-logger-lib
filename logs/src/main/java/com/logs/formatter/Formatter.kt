package com.logs.formatter

/**
 * Formatter is used for format the data. It maybe string or maybe long and any other type of data.
 */
interface Formatter<T> {

    fun format(data: T): String
}