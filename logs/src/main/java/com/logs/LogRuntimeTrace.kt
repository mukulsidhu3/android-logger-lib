package com.logs

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import com.logs.fileprinter.file.FilePrinter
import com.logs.fileprinter.file.naming.DateFileNameGenerator
import com.logs.fileprinter.file.writer.SimpleWriter
import java.lang.ref.WeakReference

class LogRuntimeTrace(
    val activity: WeakReference<Activity>? = null
) {
    var application: Application? = null
    fun registerForCallback(application: Application) {
        this.application = application
        application.registerActivityLifecycleCallbacks(activityCallback)
    }

    fun unregisterOrUnregisterUncaughtExceptionHandler(isRegister: Boolean) {
        activity.apply {
            Thread.setDefaultUncaughtExceptionHandler(if (isRegister) thread else null)
        }
    }

    private val activityCallback = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

            unregisterOrUnregisterUncaughtExceptionHandler(true)
        }

        override fun onActivityStarted(activity: Activity) {}

        override fun onActivityResumed(activity: Activity) {
        }

        override fun onActivityPaused(activity: Activity) {}

        override fun onActivityStopped(activity: Activity) {}

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

        override fun onActivityDestroyed(activity: Activity) {
            /**
             * When any activity destroy happens,
             * remove [Activity] reference and [unregisterOrUnregisterUncaughtExceptionHandler]
             */
            unregisterOrUnregisterUncaughtExceptionHandler(false)

        }

    }

    private val thread = Thread.UncaughtExceptionHandler { _, e ->

        activity.let {
            /*Write a new log to the cache directory if exception is an RuntimeException
            and config is set to true*/
            val printer = FilePrinter.Builder(
                Constants.FILE_PATH
            ).fileNameGenerator(DateFileNameGenerator())
                .writer(SimpleWriter())
                .build()
            if (e is RuntimeException) {
                printer.printRuntimeTrace(e.message.toString())
                Log.d("RunTimeThread", e.message.toString())

            } else {

                printer.printRuntimeTrace(e.message.toString())
                Log.d("RunTimeThread", e.message.toString())
                // notifyClient(it, t, e)
            }
        }
    }
}