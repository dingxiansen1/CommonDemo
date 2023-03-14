package com.dd.utils.log

import android.util.Log
import com.dd.utils.BuildConfig
import com.dd.utils.log.LogUtils.Type.*
import com.dd.utils.log.LogUtils.enabled
import com.dd.utils.log.LogUtils.logHooks
import com.dd.utils.log.LogUtils.tag
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.min

/**
 * @property tag 默认日志标签
 * @property enabled 日志全局开关
 * @property logHooks 日志拦截器
 */
object LogUtils {

    enum class Type {
        VERBOSE, DEBUG, INFO, WARN, ERROR, WTF
    }

    private const val TOP_CORNER = "┌────────────────────────────────────────────────────────"
    private const val MIDDLE_CORNER = "├────────────────────────────────────────────────────────"
    private const val LEFT_BORDER = "│ "
    private const val BOTTOM_CORNER = "└────────────────────────────────────────────────────────"

    /** 日志默认标签 */
    var tag = "日志"

    /** 是否启用日志 */
    var enabled = true

    /** 日志是否显示代码位置 */
    var traceEnabled = true

    /** 日志的Hook钩子 */
    val logHooks by lazy { ArrayList<LogHook>() }

    /**
     *开始日志
     **/
    fun init(enabled: Boolean = BuildConfig.DEBUG, tag: String = this.tag) {
        setDebug(enabled)
    }

    /**
     * @param enabled 是否启用日志
     * @param tag 日志默认标签
     */
    private fun setDebug(enabled: Boolean, tag: String = this.tag) {
        this.enabled = enabled
        this.tag = tag
    }

    //<editor-fold desc="Hook">
    /**
     * 添加日志拦截器
     */
    fun addHook(hook: LogHook) {
        logHooks.add(hook)
    }

    /**
     * 删除日志拦截器
     */
    fun removeHook(hook: LogHook) {
        logHooks.remove(hook)
    }

    @JvmOverloads
    @JvmStatic
    fun v(
        tag: String,
        msg: Any?,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {

        print(VERBOSE, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun i(
        tag: String,
        msg: Any?,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(INFO, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun d(
        tag: String,
        msg: Any?,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(DEBUG, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun w(
        tag: String,
        msg: Any?,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(WARN, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun e(
        tag: String,
        msg: Any?,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(ERROR, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun e(
        tag: String,
        tr: Throwable? = null,
        occurred: Throwable? = Exception(),
        msg: Any? = "",
    ) {
        print(ERROR, msg, tag, tr, occurred)
    }

    @JvmOverloads
    @JvmStatic
    fun wtf(
        tag: String,
        msg: Any?,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        print(WTF, msg, tag, tr, occurred)
    }

    /**
     * 输出日志
     * 如果[msg]和[occurred]为空或者[tag]为空将不会输出日志, 拦截器
     *
     * @param type 日志等级
     * @param msg 日志信息
     * @param tag 日志标签
     * @param occurred 日志异常
     */
    @Synchronized
    private fun print(
        type: Type = INFO,
        msg: Any? = null,
        tag: String = this.tag,
        tr: Throwable? = null,
        occurred: Throwable? = Exception()
    ) {
        if (!enabled || msg == null) return
        var message = msg.toString()
        val info = LogInfo(type, message, tag, tr, occurred)
        for (logHook in logHooks) {
            logHook.hook(info)
            if (info.msg == null) return
        }

        if (traceEnabled && occurred != null) {
            occurred.stackTrace.getOrNull(1)?.run {
                log(type, TOP_CORNER, tag, tr)
                log(type, "$LEFT_BORDER $className ($fileName:$lineNumber)", tag, tr)
                log(type, MIDDLE_CORNER, tag, tr)
            }
        }
        val max = 3800
        val length = message.length
        if (length > max) {
            var startIndex = 0
            var endIndex = max
            while (startIndex < length) {
                endIndex = min(length, endIndex)
                val substring = message.substring(startIndex, endIndex)
                log(type, LEFT_BORDER + substring, tag, tr)
                startIndex += max
                endIndex += max
            }
        } else {
            log(type, LEFT_BORDER + message, tag, tr)
        }
        log(type, BOTTOM_CORNER, tag, tr)
    }

    /**
     * JSON格式化输出日志
     * @param tag 日志标签
     * @param type 日志类型
     * @param occurred 日志发生位置
     */
    @JvmOverloads
    @JvmStatic
    fun json(
        tag: String,
        jsonString: String?,
        type: Type = INFO,
        occurred: Throwable? = Exception()
    ) {
        if (jsonString == null) {
            print(type, "json is null", tag, occurred = null)
            return
        }
        try {
            var json = jsonString.trim()
            if (json.startsWith("{")) {
                val jsonObject: JSONObject = JSONObject(json)
                val message = jsonObject.toString(2)
                d(tag, message, occurred)
                return
            }
            if (json.startsWith("[")) {
                val jsonArray = JSONArray(json)
                val message = jsonArray.toString(2)
                d(tag, message)
                return
            }
            e("Invalid Json")
        } catch (e: JSONException) {
            e("Invalid Json")
        }
    }

    private fun log(type: Type, msg: String, tag: String, tr: Throwable?) {
        when (type) {
            VERBOSE -> Log.v(tag, msg, tr)
            DEBUG -> Log.d(tag, msg, tr)
            INFO -> Log.i(tag, msg, tr)
            WARN -> Log.w(tag, msg, tr)
            ERROR -> Log.e(tag, msg, tr)
            WTF -> Log.wtf(tag, msg, tr)
        }
    }
}