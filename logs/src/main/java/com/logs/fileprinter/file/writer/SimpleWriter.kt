package com.logs.fileprinter.file.writer

import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

/**
 * For initialize work to new file and add file header.
 */
open class SimpleWriter : Writer() {

    //Name of log file.
    private var logFileName: String? = null

    //Log file.
    private var logFile: File? = null


    private var bufferedWriter: BufferedWriter? = null


    override fun open(file: File?): Boolean {
        logFileName = file!!.name
        logFile = file

        var isNewFile = false

        // Create log file if not exists.
        if (!logFile!!.exists()) {
            isNewFile = try {
                val parent = logFile!!.parentFile
                if (parent != null) {
                    if (!parent.exists()) {
                        parent.mkdirs()
                    }
                }
                logFile!!.createNewFile()
                true
            } catch (e: Exception) {
                e.printStackTrace()
                close()
                return false
            }
        }

        // Create buffered writer.
        try {
            bufferedWriter = BufferedWriter(FileWriter(logFile, true))
            if (isNewFile) {
                onNewFileCreated(logFile!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            close()
            return false
        }
        return true
    }

    override fun isOpened(): Boolean {
        return bufferedWriter != null && logFile!!.exists()
    }

    override fun getOpenedFile(): File {
        return logFile!!
    }

    override fun getOpenedFileName(): String? {
        return logFileName
    }

    override fun appendLog(log: String): Boolean {
        Log.d("WriterW", "work")
        try {
            bufferedWriter!!.write(log)
            bufferedWriter!!.newLine()
            bufferedWriter!!.flush()
            return true
        } catch (e: java.lang.Exception) {
           // Platform.get().warn("append log failed: " + e.message)
            e.printStackTrace()
            return false
        }
    }

    override fun close(): Boolean {
        if (bufferedWriter != null) {
            try {
                bufferedWriter!!.close()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
        bufferedWriter = null
        logFileName = null
        logFile = null
        return true
    }

    //We can do some initialization work to the new file, such as calling appendLog
    @Override
    open fun onNewFileCreated(file: File){}

}