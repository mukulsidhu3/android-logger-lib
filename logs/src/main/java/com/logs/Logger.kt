package com.logs

import android.util.Log
import com.logs.fileprinter.Printer
import com.logs.formatter.message.json.JsonFormatter
import com.logs.formatter.message.xml.XmlFormatter
import com.logs.formatter.stacktrace.DefaultStackTraceFormatter
import com.logs.utils.StackTraceUtil

class Logger {

    private var logConfig: LogConfig? = null

    //Log printer used to print the logs.
    private var printer: Printer? = null

    constructor(logConfig: LogConfig, printer: Printer) {
        this.logConfig = logConfig
        this.printer = printer
    }

    constructor(builder: Logger.Builder){
        val logConfigBuilder: LogConfig.Builder? = null

        if (builder.logLevel != 0) {
            logConfigBuilder?.logLevel(builder.logLevel)
        }

        if (builder.tag != null) {
            logConfigBuilder?.tag(builder.tag!!)
        }

        if (builder.jsonFormatter != null){
            logConfigBuilder?.jsonFormatter(builder.jsonFormatter!!)
        }

        if (builder.xmlFormatter != null){
            logConfigBuilder?.xmlFormatter(builder.xmlFormatter!!)
        }

        if (builder.printer != null) {
            printer = builder.printer
        }
    }

    class Builder {

        //Logs below of which would not be printed.
        var logLevel = 0

        // The tag string when [Logger] log.
        var tag: String? = null

        //The JSON formatter when log a JSON string.
         var jsonFormatter: JsonFormatter? = null

        //The XML formatter when [Logger] log a XML string.
         var xmlFormatter: XmlFormatter? = null

        //Printer used to print the log
        var printer: Printer? = null

        fun logLevel(logLevel: Int): Builder {
            this.logLevel = logLevel
            return this
        }

        //Set the tag string when log.
        fun tag(tag: String?): Builder {
            this.tag = tag
            return this
        }

        fun jsonFormatter(jsonFormatter: JsonFormatter?): Builder {
            this.jsonFormatter = jsonFormatter
            return this
        }

        //Set the XML formatter when log a XML string.
        fun xmlFormatter(xmlFormatter: XmlFormatter?): Builder {
            this.xmlFormatter = xmlFormatter
            return this
        }

        fun printers(printers: Printer?): Builder {
            this.printer = printers
            return this
        }


        fun build(): Logger {
            return Logger(this)
        }


    }

    /*package*/
    fun println(logLevel: Int, msg: String?) {
     /*   if (logLevel < logConfig!!.logLevel) {
            return
        }  */
        printInternal(logLevel, msg ?: "")
    }

    private fun printInternal(logLevel: Int, msg: String) {

        val stackTrace = StackTraceUtil().getCroppedRealStackTrace(Throwable().stackTrace,"kk",5)
        val stackTraceString = DefaultStackTraceFormatter().format(stackTrace)

        val string = msg + stackTraceString
       printer!!.println(logLevel,"TAG",string, "MAin")

        Log.d("CheckLogger", string)
    }


}