package com.dd.net.okhttp

open class HttpResponse @JvmOverloads constructor(
    var errorCode: String = "",
    var errorMsg: String = ""
) {
    var time = 0L

    init {
        time = System.currentTimeMillis()
    }
}