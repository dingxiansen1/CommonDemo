package com.dd.utils

import android.R.attr.bitmap
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import androidx.palette.graphics.Palette
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


/**
 * 保存图片到相册
 * @param fileName 相册文件显示名称
 * @param format 图片格式
 * @param quality 图片质量
 */
private fun Bitmap.saveToPhoto(
    context: Context,
    fileName: String = System.currentTimeMillis().toString(),
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
    quality: Int = 100,
): Boolean {
    val contentValues = ContentValues()
    fileName.let {
        contentValues.put(MediaStore.MediaColumns.TITLE, fileName)
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
    }
    val mimeType = when (format) {
        Bitmap.CompressFormat.JPEG -> "image/jpeg"
        Bitmap.CompressFormat.PNG -> "image/png"
        Bitmap.CompressFormat.WEBP -> "image/webp"
        else -> "image/png"
    }
    contentValues.put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
    try {
        val uri = context.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        ) ?: return false
        context.sendBroadcast(Intent("com.android.camera.NEW_PICTURE", uri))
        context.contentResolver.openOutputStream(uri).use {
            compress(format, quality, it)
        }
        return true
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        return false
    }
}


/**
 * 获取bitmap的主要颜色
 **/
fun Bitmap.generateDominantColorState(): Palette.Swatch = Palette.Builder(this)
    .resizeBitmapArea(0)
    .maximumColorCount(16)
    .generate()
    .swatches
    .maxByOrNull { swatch -> swatch.population }!!
