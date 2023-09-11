package com.logs.fileprinter.file


import android.util.Log
import com.logs.LogLevel
import com.logs.fileprinter.Printer
import com.logs.fileprinter.file.naming.FileNameGenerator
import com.logs.fileprinter.file.writer.Writer
import com.logs.formatter.stacktrace.DefaultStackTraceFormatter
import com.logs.utils.StackTraceUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Use the Builder to construct a FilePrinter object for create a file with mobile device information.
 */

class FilePrinter internal constructor(builder: Builder) : Printer {

    //Folder path of log file.
    private var folderPath: String = "FOLDER_PATH"

    //For the file name generator of log file.
    private val fileNameGenerator: FileNameGenerator

    //To write log into log file.
    private val writer: Writer

    init {
        folderPath = builder.folderPath
        fileNameGenerator = builder.fileNameGenerator!!
        writer = builder.writer!!

    }


    //Builder for FilePrinter
    class Builder(var folderPath: String) {

        //For the file name generator of log file
        var fileNameGenerator: FileNameGenerator? = null

        //To write log into log file.
        var writer: Writer? = null

        //Set the file name generator for log file.
        fun fileNameGenerator(fileNameGenerator: FileNameGenerator): Builder {
            this.fileNameGenerator = fileNameGenerator
            return this
        }

        //Set the writer to write log into log file.
        fun writer(writer: Writer?): Builder {
            writer!!.open(File(folderPath, "LogFile"))
            this.writer = writer
            return this
        }

        //Build configured FilePrinter object.
        fun build(): FilePrinter {
            // fillEmptyField()
            return FilePrinter(this)
        }

        /*
        fun fillEmptyField(){
            if (fileNameGenerator == null){
                fileNameGenerator =
            }
        }   */

    }

    private val channel = Channel<Boolean>(10000)
    override fun println(logLevel: String, tag: String, msg: String) {

        val currentTime = getCurrentTime()
        val string = "\n$currentTime, $logLevel , $tag, $msg"
        doPrintln(string)

    }

    fun printRuntimeTrace(msg: String){
        val stackTrace = StackTraceUtil().getCroppedRealStackTrace(Throwable().stackTrace, "kk", 5)
        val stackTraceString = DefaultStackTraceFormatter().format(stackTrace)

        val string = msg + stackTraceString
      //  println("Exception", "Runtime Exception", string)
        val currentTime = getCurrentTime()
        val stringTrace = "\n$currentTime, Exception , Runtime Exception, $string"

        writer.appendLog(stringTrace)

    }


    private fun doPrintln(string: String) {
        MainScope().launch {
            channel.send(writer.appendLog(string))
        }
    }

    private fun getCurrentTime(): String {
        val time = System.currentTimeMillis()
        return SimpleDateFormat("dd/MM/yyy HH:mm:ss:SSS 'T'z", Locale.getDefault()).format(time)
    }
}