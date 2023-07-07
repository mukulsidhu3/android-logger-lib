package com.logs.storage

import android.os.Environment
import android.os.StatFs
import android.util.Log
import java.io.File

class StorageCheck: Storage {

    override fun getInternalMemoryInfo(): Long {
        val path: File = Environment.getDataDirectory()
        val stat = StatFs(path.path)
        val blockSize = stat.blockSizeLong
        val availableBlocks = stat.availableBlocksLong
        val totalBlocks = stat.blockCountLong
        val availableSpace = formatSize(availableBlocks * blockSize)
        val totalSpace = formatSize(totalBlocks * blockSize)

        return availableSpace
    }

    override fun formatSize(size: Long): Long {
        var totalSize = size

        totalSize /= 1024
        totalSize /= 1024

        return size
    }
}