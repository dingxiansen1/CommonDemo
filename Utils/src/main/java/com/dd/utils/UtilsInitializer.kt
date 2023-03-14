package com.dd.utils

import android.app.Application
import android.content.Context
import androidx.startup.Initializer

class UtilsInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        Utils.init(context as Application)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}