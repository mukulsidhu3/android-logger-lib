package com.logs.fileprinter.file


import android.app.Application
import androidx.lifecycle.Lifecycle
import com.logs.fileprinter.Printer
import com.logs.fileprinter.file.naming.FileNameGenerator
import com.logs.fileprinter.file.writer.Writer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.coroutines.CoroutineContext

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
    override fun println(logLevel: Int, tag: String, msg: String, classTag: String) {
        val time = System.currentTimeMillis()

        val string = "$time, $logLevel , $tag, $msg, $classTag"

        MainScope().launch {
            channel.send(writer.appendLog(string))
        }


    }


    fun doPrintln(){

    }
}