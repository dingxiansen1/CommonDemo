package com.dd.utils.toast

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.StringRes
import com.dd.utils.Utils

private var mToast: Toast? = null

/**
 * 显示时间较短的吐司
 *
 * @param text String，显示的内容
 */
fun String.showToast(context: Context = Utils.getApp()) {
    if (TextUtils.isEmpty(this)) return
    if (Thread.currentThread() === Looper.getMainLooper().thread) {
        showToast(context, this, Toast.LENGTH_SHORT)
    } else {
        Handler(context.mainLooper).post { showToast(context, this, Toast.LENGTH_SHORT) }
    }
}

fun @receiver:StringRes Int.showToast(context: Context = Utils.getApp()) {
    if (this <= 0) return
    if (Thread.currentThread() === Looper.getMainLooper().thread) {
        showToast(context, this, Toast.LENGTH_SHORT)
    } else {
        Handler(context.mainLooper).post { showToast(context, this, Toast.LENGTH_SHORT) }
    }
}

/**
 * 显示时间较长的吐司
 *
 * @param text String，显示的内容
 */
fun String.showLongToast(context: Context = Utils.getApp()) {
    if (TextUtils.isEmpty(this)) return
    if (Thread.currentThread() === Looper.getMainLooper().thread) {
        showToast(context, this, Toast.LENGTH_LONG)
    } else {
        Handler(context.mainLooper).post { showToast(context, this, Toast.LENGTH_LONG) }
    }
}

fun @receiver:StringRes Int.showLongToast(context: Context = Utils.getApp()) {
    if (this <= 0) return
    if (Thread.currentThread() === Looper.getMainLooper().thread) {
        showToast(context, this, Toast.LENGTH_LONG)
    } else {
        Handler(context.mainLooper).post { showToast(context, this, Toast.LENGTH_LONG) }
    }
}

private fun showToast(context: Context? = Utils.getApp(), text: Int, duration: Int) {
    if (text <= 0) return
    cancelToast()
    if (mToast == null) {
        mToast = Toast.makeText(context, null as CharSequence?, duration)
    }
    mToast?.apply {
        setText(text)
        this.duration = duration
        show()
    }
}

private fun showToast(context: Context? = Utils.getApp(), text: String?, duration: Int) {
    if (TextUtils.isEmpty(text)) return
    cancelToast()
    if (mToast == null) {
        mToast = Toast.makeText(context, null as CharSequence?, duration)
    }
    mToast?.apply {
        setText(text)
        this.duration = duration
        show()
    }
}

fun cancelToast() {
    mToast?.cancel()
}




