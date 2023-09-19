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


    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        Log.Companion.createFilePrinter(
            BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE,
            this
        )

        val logger = Log.init()

        //  permission()
        //initLog()
        //  StorageCheck().getInternalMemoryInfo()
        //  LogWritePermission().checkPermission(this)
        //  initLog()


        viewBinding.sendEmailButton.setOnClickListener {
            //  ShareViaEmail().sendEmail(this,"com.logger.provider")
            // stackTraceFormat()
            //   checkLogClass(logger)
            uncua(logger)
            //  xmlFormat("<PrintLetterBarcodeData uid=\"633830176228\" name=\"Mukul Sidhu\" gender=\"M\" yob=\"2000\" co=\"S/O Jashpal Singh\" loc=\"Singhpura\" vtc=\"Singhpura\" po=\"Safidon\" dist=\"Jind\" subdist=\"Safidon\" state=\"Haryana\" pc=\"126112\" dob=\"01/04/2000\"/>")
        }


    }

    fun uncua(logger: Logger) {

      /*  try {
            val cl = 0
            val b = 1;
            val c = b/cl
        }catch (e: Exception){
            android.util.Log.d("checkCatch", "uncua")
            logger.customLog("Mukul", "SYNC", "MSG")

        }  */
        logger.customLog("Mukul", "SYNC", "MSG")
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