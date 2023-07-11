package com.logs.sharefile

import android.content.Context

interface ShareFile {
    fun sendEmail(context: Context, provider: String)
}