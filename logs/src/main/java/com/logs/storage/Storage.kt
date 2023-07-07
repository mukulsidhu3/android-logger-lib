package com.logs.storage

interface Storage {

    fun getInternalMemoryInfo(): Long

    fun formatSize(size: Long): Long
}