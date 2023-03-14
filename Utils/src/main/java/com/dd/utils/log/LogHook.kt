package com.dd.utils.log

/** 拦截日志 */
interface LogHook {

    fun hook(info: LogInfo)

}