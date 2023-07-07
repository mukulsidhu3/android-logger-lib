package com.logs.fileprinter.file.naming

/**
 * Generates names for log files.
 */
interface FileNameGenerator {

   /**
    * Check the generated file name will change or not.
    */
   fun isFileNameChangeable(): Boolean

   /**
    * Generate file name for specified log level and timestamp.
    */
   fun generateFileName(logLevel: Int, timeStamp: Long): String

}