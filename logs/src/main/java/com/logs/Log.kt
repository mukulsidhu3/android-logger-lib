package com.logs

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import com.logs.Constants.Companion.FILE_PATH
import com.logs.fileprinter.file.FilePrinter
import com.logs.fileprinter.file.naming.DateFileNameGenerator
import com.logs.fileprinter.file.path.FileDirectory
import com.logs.fileprinter.file.writer.SimpleWriter
import java.io.File

class Log() {
    var logger: Logger? = null

    init {

        logger = Logger(Logger.Builder().
        printers(getFilePrinter())
            .logLevel(1)
            .tag("LOG_LIBRARY"))


    }

    companion object{

        fun createFilePrinter(versionName: String, versionCode: Int, context: Context) {
            FILE_PATH = FileDirectory(context).getFilePath()
            val filePrinter = FilePrinter.Builder(
                FILE_PATH
            ).fileNameGenerator(DateFileNameGenerator())
                .writer(object : SimpleWriter() {
                    @Override
                    override fun onNewFileCreated(file: File) {
                        super.onNewFileCreated(file)
                        val header = BuildConfigConstant.getDeviceInformation(
                            versionName,
                            versionCode
                        )
                        appendLog(header)
                    }
                })
                .build()
        }
    }



    fun getFilePrinter(): FilePrinter {

        val filePrinter = FilePrinter.Builder(
            FILE_PATH
        ).fileNameGenerator(DateFileNameGenerator())
            .writer(SimpleWriter())
            .build()

        return filePrinter
    }
}