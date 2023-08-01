package com.logs.formatter.message.`object`

import android.content.Intent
import com.logs.utils.ObjectToStringUtil

class IntentFormatter : ObjectFormatter<Intent> {
    override fun format(data: Intent): String {
        return ObjectToStringUtil().intentToString(data)
    }
}