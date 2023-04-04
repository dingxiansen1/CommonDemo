package com.dd.basiccompose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T : Any> DefaultList(
    lazyPagingItems: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    errorView: (@Composable () -> Unit)? = null,
    loadingView: (@Composable () -> Unit)? = null,
    noMoreView: (@Composable () -> Unit)? = null,
    emptyView: (@Composable (LazyPagingItems<T>) -> Unit) = {},
    itemContent: LazyListScope.() -> Unit,
) {
    if (lazyPagingItems.itemCount > 0) {
        LazyColumn(
            modifier = modifier, state = lazyListState
        ) {
            //条目布局
            itemContent()
            //加载更多状态：加载中和加载错误,没有更多

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
    } else {
        Box(modifier = modifier) {
            emptyView.invoke(lazyPagingItems)
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
