package com.dd.common.manager

import android.annotation.SuppressLint
import android.os.Environment
import android.util.Log
import com.dd.utils.ApplicationUtils
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.*


/**
 * 崩溃管理类
 **/
object CrashManager : Thread.UncaughtExceptionHandler {

    private const val TAG = "CrashManager"

    /**
     * 系统默认UncaughtExceptionHandler
     */
    private var mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()

    private var mCrashCallback: ICrashCallback? = null

    /**
     * 存储异常和参数信息
     */
    private val paramsMap by lazy {
        val map = HashMap<String, String>()
        ApplicationUtils.appInfo.let {
            map["versionName"] = it.versionName
            map["versionCode"] = it.versionCode.toString()
        }
        map
    }


    init {
        //设置该CrashHandler为系统默认的
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    fun init(mCrashCallback: ICrashCallback?) {
        this.mCrashCallback = mCrashCallback
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        val filePath = saveCrashInfoToFile(e)
        mCrashCallback?.crashCallback(filePath)
        mDefaultHandler?.uncaughtException(t, e)
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return  返回文件名称,便于将文件传送到服务器
     */
    private fun saveCrashInfoToFile(ex: Throwable): String? {
        val sb = StringBuffer()
        for ((key, value) in paramsMap) {
            sb.append(key).append("=").append(value).append("\n")
        }
        val writer = StringWriter()
        val printWriter = PrintWriter(writer)
        ex.printStackTrace(printWriter)
        var cause = ex.cause
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        printWriter.close()
        val result: String = writer.toString()
        sb.append(result)
        try {
            val time: String = timeStampDate()
            val fileName = "crash-$time.log"
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                val path: String =
                    Environment.getExternalStorageDirectory().absolutePath + "/crash/"
                val dir = File(path)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                val fos = FileOutputStream(path + fileName)
                fos.write(sb.toString().toByteArray())
                fos.close()
                return path + fileName
            } else {
                return null
            }
        } catch (e: Exception) {
            Log.e(TAG, "an error occured while writing file...", e)
        }
        return null
    }

    /**
     * 时间戳转换成日期格式字符串
     * 格式 - 2021-08-05 13:59:05
     */
    @SuppressLint("SimpleDateFormat")
    fun timeStampDate(): String {
        val nowTime = Date(System.currentTimeMillis())
        val sdFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:dd")
        return sdFormatter.format(nowTime) ?: nowTime.toString()
    }

    interface ICrashCallback {

        fun crashCallback(filePath: String?)

    }


}