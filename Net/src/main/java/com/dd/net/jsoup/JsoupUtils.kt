package com.dd.net.jsoup

import com.dd.net.okhttp.newCallResponseBody
import com.dd.utils.log.LogUtils
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.concurrent.TimeoutException


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
            LogUtils.d(TAG,"coroutineExceptionHandler ：" +throwable.message.toString())
        }
    /**
     * 根据 url 解析html获取内容
     * @link  目标地址解析后的Document节点
     * @retry  失败重试
     **/
    suspend fun getHtmlByOkhttpFlow(link: String, retry: Int = 3): Flow<Document?> {
        return flow {
            val html = getProxyClient().newCallResponseBody(retry) {
                url(link)
            }
            val data = Jsoup.parse(html.toString())
            emit(data)
        }
    }

    /**
     * 根据 url 解析html获取内容
     * @link  目标地址解析后的Document节点
     * @retry  失败重试
     **/
    suspend fun getHtmlByOkhttp(link: String, retry: Int = 3): Document? {
        val html = getProxyClient().newCallResponseBody(retry) {
            url(link)
        }
        return Jsoup.parse(html.toString())
    }
    /**
     * 根据 url 解析html获取内容
     * @url  目标地址解析后的Document节点
     * @retry  失败重试
     **/
    suspend fun getHtmlByJsoup(url: String): Document? {
        return withContext(Dispatchers.Default+coroutineExceptionHandler) {
            return@withContext Jsoup.connect(url)
                .timeout(1000 * 30)
                .header(HtmlConstant.Keep_Alive_Name, HtmlConstant.Keep_Alive_Value)
                .header(HtmlConstant.Connection_Name, HtmlConstant.Connection_Value)
                .header(HtmlConstant.Cache_Control_Name, HtmlConstant.Cache_Control_Value)
                .get()
        }
    }

    /**
     * 根据 url 解析html获取内容
     * @url  目标地址解析后的Document节点
     * @retry  失败重试
     **/
    suspend fun postHtmlByJsoup(url: String, retry: Long = 3): Flow<Document?> {
        return flow {
            val data = Jsoup
                .connect(url)
                .timeout(1000 * 5)
                .header(HtmlConstant.Keep_Alive_Name, HtmlConstant.Keep_Alive_Value)
                .header(HtmlConstant.Connection_Name, HtmlConstant.Connection_Value)
                .header(HtmlConstant.Cache_Control_Name, HtmlConstant.Cache_Control_Value)
                .post()
            emit(data)
        }.retry(retry) {
            it is TimeoutException
        }
    }

    /**
     * 通过规则来让选择器筛选正确的文本内容
     * @documend  目标地址解析后的Document节点
     * @rules  规则
     **/
    suspend fun getTextByRulesSelect(documend: Document, rules: String): Flow<String> {
        return flow {
            emit(documend.select(rules).text())
        }
    }


}