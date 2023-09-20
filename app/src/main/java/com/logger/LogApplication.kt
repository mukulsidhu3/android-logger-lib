package com.logger

import android.app.Activity
import android.app.Application
import com.logs.Log
import com.logs.LogConfig
import com.logs.LogRuntimeTrace

class LogApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        /**
         * Register log runtime trace for write exception message in log file.
         */
        LogRuntimeTrace().registerForCallback(this)
    }

}