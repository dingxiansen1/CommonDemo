WebSettings webSettings = mWebView .getSettings();

//支持获取手势焦点，输入用户名、密码或其他
webview.requestFocusFromTouch();

setJavaScriptEnabled(true);  //支持js
setPluginsEnabled(true);  //支持插件

webSettings.setRenderPriority(RenderPriority.HIGH);  //提高渲染的优先级

设置自适应屏幕，两者合用
setUseWideViewPort(true);  //将图片调整到适合webview的大小
setLoadWithOverviewMode(true); // 缩放至屏幕的大小

setSupportZoom(true);  //支持缩放，默认为true。是下面那个的前提。
setBuiltInZoomControls(true); //设置可以缩放
setDisplayZoomControls(false); //隐藏原生的缩放控件
//若上面是false，则该WebView不可缩放，这个不管设置什么都不能缩放。
setTextZoom(2);//设置文本的缩放倍数，默认为 100

setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
supportMultipleWindows();  //多窗口
setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
setAllowFileAccess(true);  //设置可以访问文件
setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
setLoadsImagesAutomatically(true);  //支持自动加载图片
setDefaultTextEncodingName("utf-8");//设置编码格式

setStandardFontFamily("");//设置 WebView 的字体，默认字体为 "sans-serif"
setDefaultFontSize(20);//设置 WebView 字体的大小，默认大小为 16
setMinimumFontSize(12);//设置 WebView 支持的最小字体大小，默认为 8