package com.dd.net.jsoup

import com.dd.net.okhttp.newCallResponseBody
import com.dd.net.okhttp.okHttpClient
import com.dd.utils.log.LogUtils
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


/**
 *
 *
 *
 *
 **/

object JsoupUtils {

    const val TAG = "JsoupUtils"

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            LogUtils.d(TAG, "coroutineExceptionHandler ：" + throwable.message.toString())
        }

    /**
     * 根据 url 解析html获取内容
     * @link  目标地址解析后的Document节点
     * @retry  失败重试
     **/
    suspend fun getHtmlByOkhttpFlow(link: String, retry: Int = 3): Flow<Document?> {
        return flowOf(getHtmlByOkhttp(link, retry))
    }

    /**
     * 根据 url 解析html获取内容
     * @link  目标地址解析后的Document节点
     * @retry  失败重试
     **/
    suspend fun getHtmlByOkhttp(link: String, retry: Int = 3): Document? {
        val html = okHttpClient.newCallResponseBody(retry) {
            url(link)
        }.string()
        return Jsoup.parse(html)
    }


}