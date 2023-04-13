package com.dd.common.utils

import com.dd.utils.log.LogUtils
import kotlinx.coroutines.*

object CoroutineUtils {

    const val TAG = "CoroutineUtils"

    val errorHandler by lazy {
        CoroutineExceptionHandler { _, throwable ->
            if (throwable !is CancellationException) {
                var error = ""
                for (e in throwable.stackTrace) {
                    error += "e" + "\n"
                }
                LogUtils.e(TAG, error)
            }
        }
    }
    /**
    * 返回一个负责IO工作的协程作用域
    * */
    fun getAppCoroutine() = CoroutineScope(
        SupervisorJob() + Dispatchers.IO + errorHandler
    )


}