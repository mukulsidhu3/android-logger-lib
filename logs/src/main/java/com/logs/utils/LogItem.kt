package com.logs.utils


/**
 * Represent a single log going to be printed.
 */
class LogItem {

    // Level of the log.
    var level: Int

    //The tag, should not be null.
    var tag: String

    //The formatted message;
    var message: String

    //Class tag
    var classTag: String


    constructor(level: Int, tag: String, msg: String, classTag: String) {
        this.level = level
        this.tag = tag
        this.message = msg
        this.classTag = classTag
    }

    constructor(
        level: Int,
        tag: String,
        message: String,
        classTag: StrictMath
    ) {
        this.level = level
        this.tag = tag
        this.message = message
        this.classTag = classTag.toString()
    }
}