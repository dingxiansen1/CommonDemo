package com.dd.common

import android.app.Application
import android.os.Build
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import okhttp3.OkHttpClient

open class BaseApp : Application() , ImageLoaderFactory {

    private val mImageLoader by lazy{
        ImageLoader.Builder(this)
            .crossfade(true)
            .okHttpClient {
                OkHttpClient.Builder()
                    .build()
            }
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

    /**
     * Coil的单例创建
     **/
    override fun newImageLoader(): ImageLoader {
        return mImageLoader
    }
}