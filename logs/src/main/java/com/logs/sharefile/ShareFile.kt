package com.logs.sharefile

import android.content.Context

/**
 * This ShareFile used to send file via email.
 */
interface ShareFile {
    fun sendEmail(context: Context, provider: String)
}