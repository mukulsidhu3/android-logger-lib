package com.logs

import com.logs.formatter.message.json.JsonFormatter
import com.logs.formatter.message.xml.XmlFormatter

class LogConfig internal constructor(val builder: Builder) {

    val logLevel: Int

    val tag: String

    val jsonFormatter: JsonFormatter

    val xmlFormatter: XmlFormatter

    init {
        logLevel = builder.logLevel
        tag = builder.tag
        jsonFormatter = builder.jsonFormatter
        xmlFormatter = builder.xmlFormatter
    }


    class Builder internal constructor(private val logConfig: LogConfig) {
        private val defaultLogLevel: Int = LogLevel.ALL

        private val defaultTag = "LOG"

        var logLevel = defaultLogLevel

        var tag: String = defaultTag

        var jsonFormatter: JsonFormatter

        var xmlFormatter: XmlFormatter

        init {
            logLevel = logConfig.logLevel
            tag = logConfig.tag
            jsonFormatter = logConfig.jsonFormatter
            xmlFormatter = logConfig.xmlFormatter

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

        fun build(): LogConfig {
            return LogConfig(this)
        }

    }


}