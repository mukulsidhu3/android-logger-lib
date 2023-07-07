package com.logger

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.logs.BuildConfigConstant
import com.logs.fileprinter.file.FilePrinter
import com.logs.fileprinter.file.naming.DateFileNameGenerator
import com.logs.fileprinter.file.path.FileDirectory
import com.logs.fileprinter.file.writer.SimpleWriter
import com.logs.permission.LogWritePermission
import com.logs.storage.StorageCheck
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  permission()
        //initLog()

        StorageCheck().getInternalMemoryInfo()
        LogWritePermission().checkPermission(this)
        initLog()
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

        for (i in 0..1000){
            Log.d("checkLoop", i.toString())
            filePrinter.println(i,"LOG","This is meesage", "MainAct.")
        }

    }





}