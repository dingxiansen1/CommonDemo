package com.dd.utils

object TimeUtils {

    fun getCurTime(): Long {
        return System.currentTimeMillis()
    }

    const val minute = 1000 * 60
    const val hour = minute * 60
    const val day = hour * 24

    /**
     * 返回距离现在系统的时间差
     * @param time 需要处理的时间戳
     * @param tip 不足一分钟返回的时间
     * Day return x天
     * Hour return x天x小时
     * Minute return x天x小时x分钟
     *
     **/
    fun getCurrentTimeSpan(time: Long,tip :String?): String {
        var timeDifference = getCurTime() - time
        var sb = StringBuilder()
        tip?.let {
            if (timeDifference < minute){
                return tip
            }
        }
        if (timeDifference / day > 0) {
            sb = sb.append("${timeDifference / day}天")
            timeDifference %= day
        }
        if (timeDifference / hour > 0) {
            sb = sb.append("${timeDifference / hour}小时")
            timeDifference %= hour
        }
        if (timeDifference / minute > 0) {
            sb = sb.append("${timeDifference / minute}分钟")
            timeDifference %= minute
        }
        return sb.toString()
    }

}