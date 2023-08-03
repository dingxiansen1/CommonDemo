package com.dd.common.manager

import androidx.work.*
import com.dd.utils.Utils
import java.util.concurrent.TimeUnit

object WorkManagerUtils {

    /**
     * 创建一次性任务
     * @param inputData work所需参数
     **/
    inline fun <reified coroutineWorker : CoroutineWorker> createOneTime(inputData: Data) {
        val workRequest = OneTimeWorkRequestBuilder<coroutineWorker>()
            .setInputData(inputData)
            .build()
        WorkManager
            .getInstance(Utils.getApp())
            .enqueue(workRequest)

    }

    /**
     * 创建定时任务,每隔span小时执行一次任务
     * @param inputData work所需参数
     * @param span 定期时间跨度
     * @param timeUnit 定期时间单位
     **/
    inline fun <reified coroutineWorker : CoroutineWorker> createTiming(
        inputData: Data,
        span: Long,
        timeUnit: TimeUnit = TimeUnit.HOURS,
    ) {
        val workRequest =
            PeriodicWorkRequestBuilder<coroutineWorker>(span, timeUnit)
                .setInputData(inputData)
                .build()
        WorkManager
            .getInstance(Utils.getApp())
            .enqueue(workRequest)

    }
}