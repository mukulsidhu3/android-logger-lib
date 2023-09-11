package com.logger

import android.app.Activity
import android.app.Application
import com.logs.LogConfig
import com.logs.LogRuntimeTrace

class LogApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        logInit()
        LogRuntimeTrace().registerForCallback(this)
    }

    private fun logInit() {
      // val logConfig =  LogConfig.Builder().
    }

}