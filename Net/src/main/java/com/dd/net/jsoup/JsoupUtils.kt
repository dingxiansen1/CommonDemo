package com.dd.net.jsoup

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.HttpException
import java.util.concurrent.TimeoutException


/**
 *
 *
 *
 *
 **/

object JsoupUtils {

    /**
     * 根据 url 解析html获取内容
     * @url  目标地址解析后的Document节点
     **/
    suspend fun getHtmlByJsoup(url: String, retry: Long = 3): Flow<Document?> {
        return flow {
            emit(Jsoup.connect(url).get())
        }.retry(retry) {
            when (it) {
                is TimeoutException -> {
                    true
                }
                is HttpException -> {
                    false
                }
                else -> {
                    false
                }
            }
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