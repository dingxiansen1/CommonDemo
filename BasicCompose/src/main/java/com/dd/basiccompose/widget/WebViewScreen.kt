package com.dd.basiccompose.widget


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.web.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    webUrl: String,
    title: String?,
    navCtrl: NavHostController,
    actions: @Composable RowScope.() -> Unit = {},
) {
    val url by remember { mutableStateOf(webUrl) }
    val state = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()
    Column {
        TopAppBar(
            title = {
                Text(
                    text = title ?: "",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp)
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    if (navigator.canGoBack) {
                        navigator.navigateBack()
                    } else {
                        navCtrl.navigateUp()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = "返回",
                    )
                }
            },
            actions = {
                actions.invoke(this)
            }
        )


        val loadingState = state.loadingState
        if (loadingState is LoadingState.Loading) {
            LinearProgressIndicator(
                progress = loadingState.progress,
                modifier = Modifier.fillMaxWidth()
            )
        }

        val webClient = remember {
            object : AccompanistWebViewClient() {
                override fun onPageStarted(
                    view: WebView?,
                    url: String?,
                    favicon: Bitmap?
                ) {
                    super.onPageStarted(view, url, favicon)
                    Log.d("Accompanist WebView", "Page started loading for $url")
                }
            }
        }

        WebView(
            state = state,
            modifier = Modifier.weight(1f),
            navigator = navigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
                //设置自适应屏幕，两者合用
                webView.settings.useWideViewPort = true //将图片调整到适合webview的大小
                webView.settings.loadWithOverviewMode = true // 缩放至屏幕的大小
                //缩放操作
                webView.settings.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。
                webView.settings.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
                webView.settings.displayZoomControls = false //隐藏原生的缩放控件
                //其他细节操作
                webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //关闭webview中缓存
                webView.settings.allowFileAccess = true //设置可以访问文件
                webView.settings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
                webView.settings.loadsImagesAutomatically = true //支持自动加载图片
                webView.settings.defaultTextEncodingName = "UTF-8"//设置编码格式
            },
            client = webClient
        )
    }
}