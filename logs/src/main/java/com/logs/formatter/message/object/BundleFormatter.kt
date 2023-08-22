package com.logs.formatter.message.`object`

import android.os.Bundle
import com.logs.utils.ObjectToStringUtil

/**
 * Format an Bundle object to a string.
 */
class BundleFormatter : ObjectFormatter<Bundle> {
    override fun format(data: Bundle): String {
        return ObjectToStringUtil().bundleToString(data)
    }
}