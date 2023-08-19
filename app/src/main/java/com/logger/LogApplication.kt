package com.logger

import android.app.Activity
import android.app.Application
import com.logs.LogConfig

class LogApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        logInit()
    }

    private fun logInit() {
       val logConfig =  LogConfig.Builder().
    }

}