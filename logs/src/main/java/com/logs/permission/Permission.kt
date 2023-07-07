package com.logs.permission

import android.content.Context

interface Permission {

    fun checkPermission(context: Context)
}