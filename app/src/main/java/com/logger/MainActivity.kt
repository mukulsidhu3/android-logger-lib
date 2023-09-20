package com.logger

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.logger.databinding.ActivityMainBinding
import com.logs.Log
import com.logs.Logger

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        permission()

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

    private fun custom(logger: Logger) {

        try {
            val cl = 0
            val b = 1
            val c = b/cl
        }catch (e: Exception){
            logger.customLog("Mukul", "SYNC", "MSG")
        }

    }

    private fun runtime(logger: Logger){
        val cl = 0
        val b = 1;
        val c = b/cl
    }



    private fun checkLogClass(logger: Logger?) {
        logger!!.v("TAG", "VERBOS")
        logger.d("TAG", "DEBUG")
        logger.i("TAG", "INFO")
        logger.e("TAG", "ERROR")
        logger.w("TAG", "WARN")
    }


    private fun permission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            activityResultLauncher.launch(
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)
            )
        } else {
            Log.Companion.createFilePrinter(
                BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE,
                this
            )
        }
    }


    private val activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions())
        { permissions ->
            // Handle Permission granted/rejected
            permissions.entries.forEach {
                val permissionName = it.key
                val isGranted = it.value
                if (isGranted) {
                    Log.Companion.createFilePrinter(
                        BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE,
                        this
                    )
                } else {
                    Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
                }
            }
        }



}