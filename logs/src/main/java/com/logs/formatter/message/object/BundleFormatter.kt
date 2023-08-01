package com.logs.formatter.message.`object`

import android.os.Bundle
import com.logs.utils.ObjectToStringUtil

class BundleFormatter : ObjectFormatter<Bundle> {
    override fun format(data: Bundle): String {
        return ObjectToStringUtil().bundleToString(data)
    }
}