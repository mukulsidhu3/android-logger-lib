package com.logger

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.logger.databinding.ActivityMainBinding
import com.logs.Log
import com.logs.Logger
import java.lang.Exception
import java.util.Calendar
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        Log.Companion.createFilePrinter(
            BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE,
            this
        )

        val logger = Log.init()

        viewBinding.customButton.setOnClickListener {
            custom(logger)
        }

        viewBinding.logButton.setOnClickListener {
            checkLogClass(logger)
        }

        viewBinding.runtimeButton.setOnClickListener {
            runtime(logger)
        }




    }

    fun custom(logger: Logger) {

        try {
            val cl = 0
            val b = 1;
            val c = b/cl
        }catch (e: Exception){
            android.util.Log.d("checkCatch", "uncua")
            logger.customLog("Mukul", "SYNC", "MSG")
        }

    }

    fun runtime(logger: Logger){
        val cl = 0
        val b = 1;
        val c = b/cl
    }



    fun checkLogClass(logger: Logger?) {
        logger!!.v("TAG", "VERBOS")
        logger.d("TAG", "DEBUG")
        logger.i("TAG", "INFO")
        logger.e("TAG", "ERROR")
        logger.w("TAG", "WARN")
    }


    fun permission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                101
            )
        } else {
            // pdfFileInPrivate()
        }
    }

}