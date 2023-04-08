package com.dd.net.okhttp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

val okHttpClient: OkHttpClient by lazy {

    val builder = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .callTimeout(60, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .sslSocketFactory(
            SSLSocketFactoryCompat(SSLSocketFactoryCompat.trustAllCert),
            SSLSocketFactoryCompat.trustAllCert
        )
    builder.build()
}

suspend fun OkHttpClient.newCallResponseBody(
    retry: Int = 0,
    builder: Request.Builder.() -> Unit
): ResponseBody {
    return newCallResponse(retry, builder).let {
        it.body ?: throw IOException(it.message)
    }
}

suspend fun OkHttpClient.newCallResponse(
    retry: Int = 0,
    builder: Request.Builder.() -> Unit
): Response {
    return withContext(Dispatchers.IO) {
        val requestBuilder = Request.Builder()
        requestBuilder.apply(builder)
        var response: Response? = null
        for (i in 0..retry) {
            response = newCall(requestBuilder.build()).await()
            if (response.isSuccessful) {
                return@withContext response
            }
        }
        return@withContext response!!
    }
}

/**
 * 通过协程suspendCancellableCoroutine同步方式拿到异步回调
 **/
suspend fun Call.await(): Response = suspendCancellableCoroutine { block ->

    block.invokeOnCancellation {
        cancel()
    }

    enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            block.resumeWithException(e)
        }

        override fun onResponse(call: Call, response: Response) {
            block.resume(response)
        }
    })

}
