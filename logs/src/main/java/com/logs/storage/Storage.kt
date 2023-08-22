package com.logs.storage

/**
 * This Storage interface used for get internal memory info and formatSize.
 */
interface Storage {

    fun getInternalMemoryInfo(): Long

    fun formatSize(size: Long): Long
}