# Logger library

A lightweight, visually appealing, powerful, and flexible logger for Android that can print logs to Logcat, Console, and Files.

# Quick Start
To initialize and create LogFile.
Write below code in activity class.

If you are working on API level 29 or below need to add read/write permission

```
   fun permission() {
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
```

For above 29 API 
```
createFilePrinter(
	BuildConfig.VERSION_NAME,
	BuildConfig.VERSION_CODE,
	this
)
```

To create a log object.

```
val log = Log.init()
```

# Logging
You can log messages in different types such as VERBOSE, DEBUG, INFO, ERROR, and WARN using the following syntax:

```
LogClassObject.<LOG_TYPE>(“TAG_TO_IDENTIFY”,”MESSAGE_OR_TROWABLE_TO_PRINT”)
```

For examples:-

To print logs like ‘VERBOS’

```
log.v("TAG","VERBOS")
```

To print logs like ‘DEBUG’

```
log.d("TAG","DEBUG")
```

To print logs like ‘INFO’

```
log.i("TAG","INFO")
```

To print logs like ‘ERROR’

```
log.e("TAG","ERROR")
```

To print logs like ‘WARN’ 
```
log.w("TAG","WARN")
```

# Custom Logs in user defined file path

You can also log custom messages with a specified file name

```
LogClassObject.customLog(FILE_NAME, TAG_TO_INDENTIFY, MESSAGE_OR_TROWABLE_TO_PRINT)
```

For example:

```
log.customLog("customLogFile","CUSTOM", "This is custom message")
```

# FileDirectory
The FileDirectory class is used to obtain the path where the LogFile is created for accessing and writing logs to it.

FilePrinter to create file and write logs

This functionality is used to write logs into a file generated at the app-specific path provided by the FileDirectory class. Example of using the builder to construct a FilePrinter object.

```
val printer = FilePrinter.Builder(
                Constants.FILE_PATH
              ).fileNameGenerator(DateFileNameGenerator())
               .writer(SimpleWriter())
               .build()
```

# JsonFormatter
The JsonFormatter class helps format unformatted JSON strings.

Example:

```
Logger().json(JSON_CONTENT)
```

# XmlFormatter

The XmlFormatter class helps format unformatted XML strings.

Example:

```
Logger().xml(XML_CONTENT)
```

# StackTraceFormatter
The StackTraceFormatter class helps format stacks of logs.

```
val stackTraceString =  DefaultStackTraceFormatter().format(stackTrace)
```

# Share File on Email

This feature allows you to share the log file via email using the ShareViaEmail class.

```
ShareViaEmail().sendEmail(this,"PACKAGE_NAME")
```

# Device internal storage check

The StorageCheck class is used to check the internal storage of the device.

```
val long = StorageCheck().getInternalMemoryInfo()
```
	
# Permission check for writing Log

The LogWritePermission class is used to check whether the app has write permission. If not, it will prompt the user to grant permission.

```
LogWritePermission().checkPermission(this)
```

# Automatic crash detection
This functionality helps developers detect and find solutions for crashes occurring in the application. By enabling this feature, the app will automatically detect crashes and write logs related to those crashes.

Use of LogRuntimeTrace class and registerForCallback function

//Write this line in onCreate method of Application Class.

```
LogRuntimeTrace().registerForCallback(this)
```

