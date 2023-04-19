package com.dd.common.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class JsonUtils {

    companion object {
        val JSON by lazy {
            Json {
                ignoreUnknownKeys = true // JSON和数据模型字段可以不匹配
                coerceInputValues = true // 如果JSON字段是Null则使用默认值
            }
        }

        inline fun <reified T> jsonToString(json: T): String {
            return JSON.encodeToString(json)
        }

        inline fun <reified T> stringToJson(string: String): T? {
            return JSON.decodeFromString(string)
        }


    }

}