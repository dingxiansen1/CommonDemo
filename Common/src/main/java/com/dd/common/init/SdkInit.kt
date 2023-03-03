package com.dd.common.init

import android.app.Application
import android.content.Context
import com.dd.common.utils.DataStoreUtils
import com.dd.utils.Utils


object SdkInit {

    fun init(context: Context) {
        DataStoreUtils.init(context)
        Utils.init(context as Application)
    }
}