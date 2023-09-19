package com.logs

import com.logs.formatter.message.json.JsonFormatter
import com.logs.formatter.message.`object`.ObjectFormatter
import com.logs.formatter.message.xml.XmlFormatter


/**
 * The configuration used for logging, always attached to a Logger, will affect all logs
 */
class LogConfig internal constructor(val builder: Builder) {


    //The log level, the logs below of which would not be printed.
    val logLevel: Int

    // The tag string.
    val tag: String

    // The JSON formatter used to format the JSON string when log a JSON string.
    val jsonFormatter: JsonFormatter

    // The XML formatter used to format the XML string when log a XML string.
    val xmlFormatter: XmlFormatter

    // Whether we should log with stack trace.
    private val withStackTrace: Boolean

    //val stackTraceOrigin: String

    // The number of stack trace elements we should log when logging with stack trace,
    // 0 if no limitation.
    private val stackTraceDepth: Int


    // The object formatters, used when logging an object.
    private val objectFormatters: Map<Class<*>, ObjectFormatter<*>>? = null

    init {
        logLevel = builder.logLevel
        tag = builder.tag
        jsonFormatter = builder.jsonFormatter!!
        xmlFormatter = builder.xmlFormatter!!
        withStackTrace = builder.withStackTrace!!
        //stackTraceOrigin = builder.stackTraceOrigin
        stackTraceDepth = builder.stackTraceDepth!!

    }


    class Builder {
        private val defaultLogLevel: Int = LogLevel.ALL

        private val defaultTag = "LOG"

        var logLevel = defaultLogLevel

        var tag: String = defaultTag

        var jsonFormatter: JsonFormatter? = null

        var xmlFormatter: XmlFormatter? = null

        var withStackTrace: Boolean? = null

        //  var stackTraceOrigin: String

        var stackTraceDepth: Int? = null

        private var objectFormatters: Map<Class<*>, ObjectFormatter<*>>? = null


        //Set the log level, the logs below of which would not be printed.
        fun logLevel(logLevel: Int): Builder {
            this.logLevel = logLevel
            return this
        }

        //Set the tag string used when log.
        fun tag(tag: String): Builder {
            this.tag = tag
            return this
        }

        //Set the JSON formatter used when log a JSON string.
        fun jsonFormatter(jsonFormatter: JsonFormatter): Builder {
            this.jsonFormatter = jsonFormatter
            return this
        }

        //Set the XML formatter used when log a XML string.
        fun xmlFormatter(xmlFormatter: XmlFormatter): Builder {
            this.xmlFormatter = xmlFormatter
            return this
        }

        //Enable stack trace, the stack trace would be printed with the log message.
        fun enableStackTrace(depth: Int): Builder {
            this.withStackTrace = true
            this.stackTraceDepth = depth
            return this
        }

        fun build(): LogConfig {
            return LogConfig(this)
        }

    }

    inline fun <reified T : Any> T.logTag() = T::class.java.simpleName

    inline fun <reified T> T.className(value: T): Class<T> {
        return T::class.java
    }

    //Get ObjectFormatter for specific object.
    fun <T> getObjectFormatter(objects: T): ObjectFormatter<in T>? {
        if (objectFormatters == null) {
            return null
        }

        var clazz: Class<in T>?
        var superClazz = className(objects)

        var formatter: ObjectFormatter<in T>
        do {
            clazz = superClazz
            formatter = objectFormatters[clazz] as ObjectFormatter<in T>
            superClazz = clazz.superclass as Class<Any?>
        } while (formatter == null && superClazz != null)
        return formatter
    }


}