package com.dd.common.init

import android.content.Context
import com.dd.common.utils.DataStoreUtils


object SdkInit {

    fun init(context: Context){
        DataStoreUtils.init(context)
    }
}