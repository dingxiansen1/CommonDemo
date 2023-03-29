package com.dd.basiccompose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.pullRefreshIndicatorTransform
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.dd.basiccompose.theme.transparent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T : Any> SwipeRefreshList(
    lazyPagingItems: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    refresh: () -> Unit,
    refreshImage: Int,
    errorView: (@Composable () -> Unit)? = null,
    loadingView: (@Composable () -> Unit)? = null,
    noMoreView: (@Composable () -> Unit)? = null,
    itemContent: LazyListScope.() -> Unit,
) {
    var refreshing by remember {
        mutableStateOf(false)
    }
    // 用协程模拟一个耗时加载
    val scope = rememberCoroutineScope()
    val state = rememberPullRefreshState(refreshing = refreshing, onRefresh = {
        scope.launch {
            refreshing = true
            refresh.invoke()
            //有BUG时间太快会卡着，暂时加延迟
            delay(1000)
            refreshing = false
        }
    })
    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(state)
    ) {
        LazyColumn(
            Modifier.fillMaxWidth(),
            state = listState
        ) {
            //条目布局
            itemContent()
            //加载更多状态：加载中和加载错误,没有更多
            if (!refreshing) {
                item {
                    lazyPagingItems.apply {
                        when (loadState.append) {
                            is LoadState.Loading -> {
                                if (loadingView != null) {
                                    loadingView.invoke()
                                } else {
                                    LoadingItem()
                                }
                            }
                            is LoadState.Error -> {
                                if (errorView != null) {
                                    errorView.invoke()
                                } else {
                                    ErrorItem { retry() }
                                }

                            }
                            is LoadState.NotLoading -> {
                                if (loadState.append.endOfPaginationReached) {
                                    if (noMoreView != null) {
                                        noMoreView.invoke()
                                    } else {
                                        NoMoreItem()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Surface(
            modifier = Modifier
                .padding(if (refreshing) 120.dp else 0.dp) //因为加载动画会缩回去导致显示不下，正在刷新时加个top
                .size(200.dp)
                .align(Alignment.TopCenter)
                .pullRefreshIndicatorTransform(state, true),
            color = transparent,
        ) {
            Box {
                if (refreshing) {
                    AsyncImage(
                        model = refreshImage,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }


}


@Composable
fun ErrorContent(retry: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Image(
                painter = painterResource(id = android.R.drawable.stat_notify_error),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Red),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "请求出错啦",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
            )
            Button(
                onClick = { retry() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
            ) {
                Text(text = "重试")
            }
        }
    }
}

@Composable
fun ErrorItem(retry: () -> Unit) {
    Button(
        onClick = { retry() },
        modifier = Modifier.padding(10.dp),
    ) {
        Text(text = "重试")
    }
}

@Composable
fun NoMoreItem() {
    Text(
        text = "没有更多了",
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(10.dp)
                .height(50.dp)
        )
    }
}
