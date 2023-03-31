# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html
#
# Starting with version 2.2 of the Android plugin for Gradle, this file is distributed together with
# the plugin and unpacked at build-time. The files in $ANDROID_HOME are no longer maintained and
# will be ignored by new version of the Android plugin for Gradle.

# Optimizations can be turned on and off in the 'postProcessing' DSL block.
# The configuration below is applied if optimizations are enabled.
# Adding optimization introduces certain risks, since for example not all optimizations performed by
# ProGuard works on all versions of Dalvik.  The following flags turn off various optimizations
# known to have issues, but the list may not be complete or up to date. (The "arithmetic"
# optimization can be used if you are only targeting Android 2.0 or later.)  Make sure you test
# thoroughly if you go this route.

####################### START #######################

# 混淆时所采用的算法(谷歌推荐算法)
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*

# 指定代码的压缩级别(在0~7之间，默认为5)
-optimizationpasses 5

# 提高优化步骤
-allowaccessmodification

# 包名不混合大小写
-dontusemixedcaseclassnames

# 不忽略非公共的库类
-dontskipnonpubliclibraryclasses

# 输出混淆日志
-verbose

# 保持 Google 原生服务需要的类不被混淆
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService
-keep public class com.google.android.vending.licensing.ILicensingService
-dontnote com.android.vending.licensing.ILicensingService
-dontnote com.google.vending.licensing.ILicensingService
-dontnote com.google.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
# 混淆注意事项第二条，保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep setters in Views so that animations can still work.
# 保留自定义控件(继承自View)不被混淆
-keepclassmembers public class * extends android.view.View {
    void set*(***);
    *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick.
# 保留在 Activity 中的方法参数是 view 的方法(避免布局文件里面 onClick 被影响)
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
# 保持枚举 enum 类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保持 Parcelable 序列化的类不被混淆(注：aidl 文件不能去混淆)
-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# 保持R(资源)下的所有类及其方法不能被混淆
-keepclassmembers class **.R$* {
    public static <fields>;
}

# Preserve annotated Javascript interface methods.
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# 支持库包含对较新版本版本的引用。
# 不要警告那些情况下,这个应用程序链接到旧的
# 平台版本。我们知道他们是安全的。
-dontnote android.support.**
-dontnote androidx.**
-dontwarn android.support.**
-dontwarn androidx.**

# 此类已弃用,但仍保留向后兼容性。
-dontwarn android.util.FloatMath

# Support包规则
# Understand the @Keep support annotation.
-keep class android.support.annotation.Keep
-keep class androidx.annotation.Keep

-keep @android.support.annotation.Keep class * {*;}
-keep @androidx.annotation.Keep class * {*;}

# 保持 support Keep 类成员不被混淆
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}

# 保持 androidx Keep 类成员不被混淆
-keepclasseswithmembers class * {
    @androidx.annotation.Keep <methods>;
}

# 保持 support Keep 类成员不被混淆
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}

# 保持 androidx Keep 类成员不被混淆
-keepclasseswithmembers class * {
    @androidx.annotation.Keep <fields>;
}

# 不混淆所有类及其类成员中的使用注解的初始化方法
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}

# 不混淆所有类及其类成员中的使用注解的初始化方法
-keepclasseswithmembers class * {
    @androidx.annotation.Keep <init>(...);
}

# 排除 android.jar 和 org.apache.http.legacy.jar 之间重复
-dontnote org.apache.http.**
-dontnote android.net.http.**

# 排除 android.jar 和核心-lambda-stubs.jar 之间重复。
-dontnote java.lang.invoke.**