package com.dd.common.share

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.text.TextUtils
import androidx.core.content.FileProvider
import java.io.File
/*
  举例：
  if (!ShareUtil.isCanJumpApplication(context,APP_PKG,SHARE_CLASS))
  {
      //跳转不了，直接调用系统分享
      ShareUtil.startShareSystemImg(context, imagePath)
      return
  }

  var intent = Intent()
  intent.setComponent(ComponentName(APP_PKG,SHARE_CLASS))
  ShareUtil.startShareImg(context, intent, java.io.File(imagePath))
  */
object ShareUtil {

    //QQ
    const val QQ_PKG = "com.tencent.mobileqq"
    const val QQ_CLASS = "com.tencent.mobileqq.activity.JumpActivity"
    //头条
    const val TouTiao_PKG = "com.ss.android.article.news"
    const val TouTiao_CLASS = "com.ss.android.publish.send.TTSendPostActivity"
    //微信朋友圈
    const val WechatCircle_PKG = "com.tencent.mm"
    const val WechatCircle_CLASS = "com.tencent.mm.ui.tools.ShareToTimeLineUI"
    //微信
    const val Wechat_PKG = "com.tencent.mm"
    const val Wechat_CLASS = "com.tencent.mm.ui.tools.ShareImgUI"
    //微博
    const val Weibo_PKG = "com.sina.weibo"
    const val Weibo_CLASS = "com.sina.weibo.composerinde.ComposerDispatchActivity"
    //百度云
    const val BaiduYun_PKG = "com.baidu.netdisk"
    const val BaiduYun_CLASS = "com.baidu.netdisk.ui.EnterShareFileActivity"
    //酷安
    const val Cool_PKG = "com.coolapk.market"
    const val Cool_CLASS = "com.coolapk.market.view.feedv8.ShareFeedV8Activity"


    private val skipSystemSharePacakgeName: MutableList<String> = ArrayList()
    private val skipPackageList: List<String>
        private get() {
            if (skipSystemSharePacakgeName.isEmpty()) {
                skipSystemSharePacakgeName.add("com.tencent.mobileqq")
                skipSystemSharePacakgeName.add("com.tencent.mm")
                skipSystemSharePacakgeName.add("com.taobao.taobao")
                skipSystemSharePacakgeName.add("com.taobao.trip")
                skipSystemSharePacakgeName.add("com.oneplus.note")
                skipSystemSharePacakgeName.add("com.android.nfc")
                skipSystemSharePacakgeName.add("com.android.bluetooth")
                skipSystemSharePacakgeName.add("com.eg.android.AlipayGphone")
                skipSystemSharePacakgeName.add("com.baidu.netdisk")
                skipSystemSharePacakgeName.add("com.sina.weibo")
                skipSystemSharePacakgeName.add("com.ss.android.article.news")
                skipSystemSharePacakgeName.add("com.douban.frodo")
                skipSystemSharePacakgeName.add("com.coolapk.market")
                skipSystemSharePacakgeName.add("com.alibaba.android.rimet")
            }
            return skipSystemSharePacakgeName
        }

    /**
     * 判断是否安装某应用
     * @param context 上下文
     * @param pkgName pkgName 包名
     * @return
     */
    fun isPkgInstalled(context: Context?, pkgName: String?): Boolean {
        var packageInfo: PackageInfo? = null
        try {
            if (context != null) {
                packageInfo = context.packageManager.getPackageInfo(pkgName!!, 0)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            packageInfo = null
            e.printStackTrace()
        }
        return packageInfo != null
    }

    /**
     * 拉起系统分享单张图片
     * @param context
     * @param path
     */
    fun startShareSystemImg(context: Context, path: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"
        val file = File(path)
        val targetUri: Uri = if (Build.VERSION.SDK_INT >= 24) {
            FileProvider.getUriForFile(
                context, context.packageName + ".provider", file
            )
        } else {
            Uri.fromFile(file)
        }
        shareIntent.putExtra(Intent.EXTRA_STREAM, targetUri)
        /* List<Intent> targetIntents =  buildShareIntents(context,shareIntent,file);
        Intent chooserIntent = Intent.createChooser(targetIntents.remove(0), "请选择分享平台");
        if (chooserIntent == null) {
            Toast.makeText(context, "无任何分享渠道", Toast.LENGTH_SHORT).show();
            return;
        }
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetIntents.toArray(new Parcelable[]{}));
        try {
            context.startActivity(chooserIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "找不到分享渠道", Toast.LENGTH_SHORT).show();
        }*/context.startActivity(Intent.createChooser(shareIntent, "请选择"))
    }

    private fun buildShareIntents(context: Context, shareIntent: Intent, file: File): List<Intent> {
        val resolveInfos: List<ResolveInfo> = context.packageManager.queryIntentActivities(
            shareIntent,
            PackageManager.MATCH_DEFAULT_ONLY
        )
        val list: MutableList<Intent> = ArrayList<Intent>()
        for (resolveInfo in resolveInfos) {
            val ainfo: ActivityInfo = resolveInfo.activityInfo
            if (skipPackageList.contains(ainfo.packageName)) {
                continue
            }
            val target = Intent(Intent.ACTION_SEND)
            target.type = "image/*"
            target.setPackage(ainfo.packageName)
            target.setClassName(ainfo.packageName, ainfo.name)
            var targetUri: Uri? = if (Build.VERSION.SDK_INT >= 24) {
                FileProvider.getUriForFile(
                    context, context.packageName + ".provider", file
                )
            } else {
                Uri.fromFile(file)
            }
            target.putExtra(Intent.EXTRA_STREAM, targetUri)
            list.add(target)
        }
        return list
    }

    /**
     * 拉起系统批量分享图片
     * @param context 上下文
     * @param filePaths
     */
    fun startShareSystemImgs(context: Context?, filePaths: List<String?>?) {
        if (context == null || filePaths == null || filePaths.isEmpty()) {
            return
        }
        val localIntent = Intent()
        val fileUris = ArrayList<Uri?>()
        for (filePath in filePaths) {
            var targetUri: Uri? = if (Build.VERSION.SDK_INT >= 24) {
                getImageContentUri(context, File(filePath))
            } else {
                Uri.fromFile(File(filePath))
            }
            fileUris.add(targetUri)
        }
        localIntent.type = "image/*"
        localIntent.action = Intent.ACTION_SEND_MULTIPLE
        localIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, fileUris)
        context.startActivity(Intent.createChooser(localIntent, "请选择"))
    }

    /**
     * 批量分享图片到某渠道
     * @param context 上下文
     * @param localIntent intent
     * @param filePaths 图片本地路径List
     */
    fun startShareMultiImg(context: Context?, localIntent: Intent?, filePaths: List<String?>?) {
        if (context == null || localIntent == null || filePaths == null || filePaths.isEmpty()) {
            return
        }
        val fileUris = ArrayList<Uri?>()
        for (filePath in filePaths) {
            if (Build.VERSION.SDK_INT >= 24) {
                fileUris.add(getImageContentUri(context, File(filePath)))
            } else {
                fileUris.add(Uri.fromFile(File(filePath)))
            }
        }
        localIntent.type = "image/*"
        localIntent.action = Intent.ACTION_SEND_MULTIPLE
        localIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, fileUris)
        context.startActivity(localIntent)
    }

    /**
     * 分享图片到某APP渠道
     * @param context 上下文
     * @param localIntent 带有某渠道的intent
     * @param file
     */
    fun startShareImg(context: Context?, localIntent: Intent?, file: File?) {
        if (context == null || localIntent == null || file == null || !file.exists()) {
            return
        }
        localIntent.type = "image/*"
        localIntent.action = Intent.ACTION_SEND
        val targetUri: Uri = if (Build.VERSION.SDK_INT >= 24) {
            FileProvider.getUriForFile(
                context, context.packageName + ".provider", file
            )
            //targetUri  = getImageContentUri(context,file);
        } else {
            Uri.fromFile(file)
        }
        localIntent.putExtra(Intent.EXTRA_STREAM, targetUri)
        context.startActivity(localIntent)
    }

    /**
     * 是否能跳转到某应用
     * @param context
     * @return
     */
    fun isCanJumpApplication(context: Context, packageName: String, className: String): Boolean {
        return if (isPkgInstalled(context, packageName)) {
            if (TextUtils.isEmpty(className) || TextUtils.isEmpty(className)) {
                return false
            }
            val testInetent = Intent()
            val comp = ComponentName(packageName, className)
            testInetent.component = comp
            testInetent.action = Intent.ACTION_SEND
            val packageManager: PackageManager = context.packageManager
            val list: List<*> = packageManager.queryIntentActivities(testInetent, 0)
            list.isNotEmpty()
        } else {
            false
        }
    }

    /**
     * Gets the content:// URI from the given corresponding path to a file
     *
     * @param context context
     * @param imageFile imageFile
     * @return content Uri
     */
    @SuppressLint("Range")
    fun getImageContentUri(context: Context, imageFile: File): Uri? {
        val filePath = imageFile.absolutePath
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Images.Media._ID),
            MediaStore.Images.Media.DATA + "=? ",
            arrayOf(filePath),
            null
        )
        var uri: Uri? = null
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID))
                val baseUri = Uri.parse("content://media/external/images/media")
                uri = Uri.withAppendedPath(baseUri, "" + id)
            }
            cursor.close()
        }
        if (uri == null) {
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DATA, filePath)
            uri =
                context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        }
        return uri
    }
}