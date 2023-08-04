package com.dd.basiccompose.widget


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.dd.common.web.WebViewHelper
import com.dd.common.web.WebViewManager
import com.dd.common.web.injectVConsoleJs
import com.google.accompanist.web.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    webUrl: String,
    title: String?,
    actions: @Composable RowScope.() -> Unit = {},
) {
    val context = LocalContext.current
    var webView by remember { mutableStateOf<WebView?>(null) }
    val state = rememberWebViewState(webUrl)
    val navigator = rememberWebViewNavigator()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
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
                    webView?.let {
                        if (!WebViewHelper.goBack(it, webUrl)) {
                            if (context is AppCompatActivity) {
                                context.onBackPressedDispatcher.onBackPressed()
                            }
                        }
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
        var progress by remember { mutableStateOf(0f) }
        AnimatedVisibility(visible = (progress > 0f && progress < 1f)) {
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        var injectState by remember { mutableStateOf(false) }
        //注入VConsole以便于H5调试
        val injectVConsole by remember { mutableStateOf(false) }

        val client = object : AccompanistWebViewClient() {

            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest?
            ): WebResourceResponse? {
                if (view != null && request != null) {
                    when {
                        WebViewHelper.isAssetsResource(request) -> {
                            return WebViewHelper.assetsResourceRequest(view.context, request)
                        }

                        WebViewHelper.isCacheResource(request) -> {
                            return WebViewHelper.cacheResourceRequest(view.context, request)
                        }
                    }
                }
                return super.shouldInterceptRequest(view, request)
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                if (view != null && request != null && request.url != null) {
                    if ("http" != request.url.scheme && "https" != request.url.scheme) {
                        try {
                            view.context.startActivity(Intent(Intent.ACTION_VIEW, request.url))
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        return true
                    }
                }
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                injectState = false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                injectState = false
            }
        }

        val chromeClient = object : AccompanistWebChromeClient() {

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                progress = (newProgress / 100f).coerceIn(0f, 1f)
                if (newProgress > 80 && injectVConsole && !injectState) {
                    view?.apply { evaluateJavascript(context.injectVConsoleJs()) {} }
                    injectState = true
                }
            }
        }

        WebView(
            state = state,
            modifier = Modifier.weight(1f),
            captureBackPresses = false,
            navigator = navigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
                val forceDarkMode =
                    AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    webView.settings.isAlgorithmicDarkeningAllowed = forceDarkMode
                } else {
                    if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                        WebSettingsCompat.setForceDark(
                            webView.settings,
                            if (forceDarkMode) WebSettingsCompat.FORCE_DARK_ON else WebSettingsCompat.FORCE_DARK_OFF
                        )
                    }
                }
                WebViewHelper.setDownloadListener(webView)
            },
            onDispose = { WebViewManager.recycle(it) },
            client = client,
            chromeClient = chromeClient,
            factory = { context -> WebViewManager.obtain(context).also { webView = it } }
        )
    }
}