package com.logger

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.logger.databinding.ActivityMainBinding
import com.logs.BuildConfigConstant
import com.logs.Logger
import com.logs.fileprinter.file.FilePrinter
import com.logs.fileprinter.file.naming.DateFileNameGenerator
import com.logs.fileprinter.file.path.FileDirectory
import com.logs.fileprinter.file.writer.SimpleWriter
import com.logs.permission.LogWritePermission
import com.logs.storage.StorageCheck
import com.logs.utils.StackTraceUtil
import java.io.File
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding


  //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        com.logs.Log.Companion.createFilePrinter(BuildConfig.VERSION_NAME,BuildConfig.VERSION_CODE,
           this
        )

        val logger = com.logs.Log(this)
      //  permission()
        //initLog()

      //  StorageCheck().getInternalMemoryInfo()
      //  LogWritePermission().checkPermission(this)
      //  initLog()



        viewBinding.sendEmailButton.setOnClickListener {
          //  ShareViaEmail().sendEmail(this,"com.logger.provider")
           // stackTraceFormat()
          //  checkLogClass(logger)
            uncua()
          //  xmlFormat("<PrintLetterBarcodeData uid=\"633830176228\" name=\"Mukul Sidhu\" gender=\"M\" yob=\"2000\" co=\"S/O Jashpal Singh\" loc=\"Singhpura\" vtc=\"Singhpura\" po=\"Safidon\" dist=\"Jind\" subdist=\"Safidon\" state=\"Haryana\" pc=\"126112\" dob=\"01/04/2000\"/>")
        }


    }

    fun uncua(){
      //  throw RuntimeException("test")
        val cal: Calendar? = null
        cal!!.time
    }


    fun checkLogClass(logger: com.logs.Log){

     //  logger.logger!!.println(1,"TAG","HEY")
        logger.logger!!.v("TAG","VERBOS")
        logger.logger!!.d("TAG","DEBUG")
        logger.logger!!.i("TAG","INFO")
        logger.logger!!.e("TAG","ERROR")
        logger.logger!!.w("TAG","WARN")
    }


    fun permission(){
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
        }else{
           // pdfFileInPrivate()
            initLog()
        }
    }

    fun initLog(){
        /**
         * LogConfiguration Task
         */

       val filePrinter = FilePrinter.Builder(
            FileDirectory(this).getFilePath()).fileNameGenerator(DateFileNameGenerator())
            .writer(object : SimpleWriter(){
                @Override
                override fun onNewFileCreated(file: File) {
                    super.onNewFileCreated(file)
                    val header = BuildConfigConstant.getDeviceInformation(BuildConfig.VERSION_NAME,
                    BuildConfig.VERSION_CODE)
                    appendLog(header)
                }
            })
            .build()



        for (i in 0..10){
            Log.d("checkLoop", i.toString())
            filePrinter.println("V","LOG","This is message")
        }


    }





}