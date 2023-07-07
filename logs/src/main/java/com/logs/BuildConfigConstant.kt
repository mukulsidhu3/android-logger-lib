package com.logs

import android.os.Build

class BuildConfigConstant {
    companion object {
        fun getDeviceInformation(versionName: String, versionCode: Int): String {

            return """ 
             
             >>>>>>>>>>>>>>>> File Header >>>>>>>>>>>>>>>>
             Device Manufacturer: ${Build.MANUFACTURER}
             Device Model       : ${Build.MODEL}
             Android Version    : ${Build.VERSION.RELEASE}
             Android SDK        : ${Build.VERSION.SDK_INT}
             App VersionName    : $versionName
             App VersionCode    : $versionCode
             <<<<<<<<<<<<<<<< File Header <<<<<<<<<<<<<<<<
             
             
             """.trimIndent()
        }
    }
}