package com.dd.common.init

import android.content.Context
import androidx.startup.Initializer

class SdkInitializer :Initializer<Unit> {
    override fun create(context: Context) {
        SdkInit.init(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}