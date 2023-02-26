package com.dd.utils

import android.R.drawable
import android.R.mipmap
import android.annotation.SuppressLint
import android.content.Context
import java.lang.reflect.Field

/**
 *通过字符串名称找到对应的资源文件名
 **/
fun String.fromDrawableGetResourcesId(): Int {
    var field: Field? = null
    try {
        field = drawable::class.java.getDeclaredField(this)
        return field.getInt(field.name)
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
    return Utils.getApp().getResourceId(this)
}
fun String.fromMipmapGetResourcesId(): Int {
    var field: Field? = null
    try {
        field = mipmap::class.java.getDeclaredField(this)
        return field.getInt(field.name)
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }

    return Utils.getApp().getResourceId(this)
}

/**
 * 获取图片名称获取图片的资源id的方法
 *
 * @param imageName  图片的名称，注意不用加图片的后缀
 * @return 返回图片资源的id
 */
@SuppressLint("DiscouragedApi")
fun Context.getResourceId(imageName: String): Int {
    return resources.getIdentifier(imageName, "drawable", this.packageName)
}