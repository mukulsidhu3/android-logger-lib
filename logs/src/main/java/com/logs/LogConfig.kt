package com.logs

import com.logs.formatter.message.json.JsonFormatter
import com.logs.formatter.message.`object`.ObjectFormatter
import com.logs.formatter.message.xml.XmlFormatter

class LogConfig internal constructor(val builder: Builder) {

    val logLevel: Int

    val tag: String

    val jsonFormatter: JsonFormatter

    val xmlFormatter: XmlFormatter

    val withStackTrace: Boolean

  //  val stackTraceOrigin: String

    val stackTraceDepth: Int

    private val objectFormatters: Map<Class<*>, ObjectFormatter<*>>? = null

    init {
        logLevel = builder.logLevel
        tag = builder.tag
        jsonFormatter = builder.jsonFormatter
        xmlFormatter = builder.xmlFormatter
        withStackTrace = builder.withStackTrace
      //  stackTraceOrigin = builder.stackTraceOrigin
        stackTraceDepth = builder.stackTraceDepth

    }


    class Builder internal constructor(private val logConfig: LogConfig) {
        private val defaultLogLevel: Int = LogLevel.ALL

        private val defaultTag = "LOG"

        var logLevel = defaultLogLevel

        var tag: String = defaultTag

        var jsonFormatter: JsonFormatter

        var xmlFormatter: XmlFormatter

        var withStackTrace: Boolean

      //  var stackTraceOrigin: String

        var stackTraceDepth: Int

        private var objectFormatters: Map<Class<*>, ObjectFormatter<*>>? = null

        init {
            logLevel = logConfig.logLevel
            tag = logConfig.tag
            jsonFormatter = logConfig.jsonFormatter
            xmlFormatter = logConfig.xmlFormatter
            withStackTrace = logConfig.withStackTrace
          //  stackTraceOrigin = logConfig.stackTraceOrigin
            stackTraceDepth = logConfig.stackTraceDepth

            if (logConfig.objectFormatters != null) {
                objectFormatters = HashMap(logConfig.objectFormatters)
            }

        }

        fun logLevel(logLevel: Int): Builder {
            this.logLevel = logLevel
            return this
        }

        fun tag(tag: String): Builder {
            this.tag = tag
            return this
        }

        fun jsonFormatter(jsonFormatter: JsonFormatter): Builder {
            this.jsonFormatter = jsonFormatter
            return this
        }

        fun xmlFormatter(xmlFormatter: XmlFormatter): Builder {
            this.xmlFormatter = xmlFormatter
            return this
        }

        fun enableStackTrace(depth: Int): Builder{
            this.withStackTrace = true
            this.stackTraceDepth = depth
            return this
        }

        fun build(): LogConfig {
            return LogConfig(this)
        }

    }

    inline fun < reified T : Any>T.logTag() = T::class.java.simpleName

    inline fun <reified T> T.className(value: T): Class<T> {
        return T::class.java
    }
    fun <T> getObjectFormatter(objects: T): ObjectFormatter<in T>? {
        if (objectFormatters == null) {
            return null
        }

        var clazz: Class<in T>?
        var superClazz = className(objects)

        var formatter: ObjectFormatter<in T>
        do {
            clazz = superClazz
            formatter = objectFormatters.get(clazz) as ObjectFormatter<in T>
            superClazz = clazz.superclass as Class<Any?>
        } while (formatter == null && superClazz != null)
        return formatter
    }


}