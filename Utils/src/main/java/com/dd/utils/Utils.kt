package com.dd.utils

import android.annotation.SuppressLint
import android.app.Application

object Utils {

    private const val TAG = "Utils"

    private var mApp: Application? = null

    fun init(app: Application) {
        if (mApp == null) {
            mApp = app
        }
    }

    fun getApp(): Application {
        if (mApp != null) {
            return mApp!!
        }
        getApplicationByReflect()?.let {
            init(it)
            return it
        }
        throw NullPointerException("$TAG getApplicationByReflect failed. mApp is null")

    }

    @SuppressLint("PrivateApi")
    fun getApplicationByReflect(): Application? {

        if (null == mApp) {
            try {
                val activityThread: Any
                val clazz = Class.forName("android.app.ActivityThread")
                val currentActivityThread = clazz.getMethod("currentActivityThread").apply {
                    isAccessible = true
                }
                val getApplication = clazz.getMethod("getApplication").apply {
                    isAccessible = true
                }
                activityThread = currentActivityThread.invoke(null)!!
                mApp = getApplication.invoke(activityThread) as Application
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        return mApp
    }
}