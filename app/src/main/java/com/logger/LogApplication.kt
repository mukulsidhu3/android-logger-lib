package com.logger

import android.app.Application
import android.os.Build
import android.util.Printer
import com.logs.fileprinter.file.FilePrinter
import com.logs.fileprinter.file.naming.DateFileNameGenerator
import com.logs.fileprinter.file.writer.SimpleWriter
import java.io.File

class LogApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }




}