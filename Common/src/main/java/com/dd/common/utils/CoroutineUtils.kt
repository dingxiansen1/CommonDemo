package com.dd.common.utils

import com.dd.utils.log.LogUtils
import kotlinx.coroutines.*

object CoroutineUtils {

    private const val TAG = "CoroutineUtils"

    private fun errorHandler(tag: String): CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            if (throwable !is CancellationException) {
                var error = ""
                for (e in throwable.stackTrace) {
                    error += e.toString() + "\n"
                }
                LogUtils.e(tag, error)
            }
        }

    /**
     * 返回一个负责IO工作的协程作用域
     * */
    fun getAppCoroutine(tag: String = TAG) = CoroutineScope(
        SupervisorJob() + Dispatchers.IO + errorHandler(tag)
    )


}