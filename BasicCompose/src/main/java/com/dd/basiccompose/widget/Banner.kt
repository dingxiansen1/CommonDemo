package com.dd.basiccompose.widget

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay

/*
* 所有banner实体类继承改接口提供banner所需要参数
* */
interface BannerData {

    fun imageUrl(): String
    fun imageLink(): String
    fun linkTitle(): String

}

/**
 * 轮播图
 * [modifier] 轮播的的样式
 * [paddingValues] 内间距
 * [looping] 是否自动轮播 默认为true
 * [timeMillis] 停留时间
 * [onClick] 轮播图点击事件
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Banner(
    list: List<BannerData>?,
    modifier: Modifier = Modifier,
    paddingValues: Dp = 0.dp,
    looping: Boolean = true,
    timeMillis: Long = 3000,
    onClick: (link: String, linkTitle: String) -> Unit
) {

    Box(
        modifier = modifier
    ) {
        if (list != null && list.isNotEmpty()) {

            val pageCount = list.size

            // We start the pager in the middle of the raw number of pages
            val loopingCount = Int.MAX_VALUE
            val startIndex = loopingCount / 2
            val pagerState = rememberPagerState(initialPage = startIndex)

            fun pageMapper(index: Int): Int {
                return (index - startIndex).floorMod(pageCount)
            }

            HorizontalPager(
                pageCount = loopingCount,
                state = pagerState,
                // Add 32.dp horizontal padding to 'center' the pages
                contentPadding = PaddingValues(horizontal = paddingValues),
                // Add some horizontal spacing between items
                pageSpacing = 4.dp,
                modifier = Modifier
                    .fillMaxSize(),
            ) { page ->
                AsyncImage(
                    model = list[pageMapper(page)].imageUrl(),
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(50.dp))
                        .clickable { //点击事件在clip后面，这样按下效果才会跟着被clip
                            with(list[pageMapper(page)]) {
                                onClick.invoke(imageLink(), linkTitle())
                            }
                        },
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }

            var underDragging by remember {
                mutableStateOf(false)
            }

            val loopState by remember {
                mutableStateOf(looping)
            }
            LaunchedEffect(key1 = Unit) {
                pagerState.interactionSource.interactions.collect { interaction ->
                    when (interaction) {
                        is PressInteraction.Press -> underDragging = true
                        is PressInteraction.Release -> underDragging = false
                        is PressInteraction.Cancel -> underDragging = false
                        is DragInteraction.Start -> underDragging = true
                        is DragInteraction.Stop -> underDragging = false
                        is DragInteraction.Cancel -> underDragging = false
                    }
                }
            }

            if (underDragging.not() && loopState) {
                LaunchedEffect(key1 = underDragging) {
                    try {
                        while (true) {
                            delay(timeMillis)
                            val current = pagerState.currentPage
                            val currentPos = pageMapper(current)
                            val nextPage = current + 1
                            if (underDragging.not()) {
                                val toPage = nextPage.takeIf { pagerState.canScrollBackward }
                                    ?: (currentPos + startIndex + 1)
                                if (toPage > current) {
                                    pagerState.animateScrollToPage(toPage)
                                } else {
                                    pagerState.scrollToPage(toPage)
                                }
                            }
                        }
                    } catch (e: CancellationException) {
                        Log.i("page", "Launched paging cancelled")
                    }
                }
            }
        }
    }
}

private fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other) * other
}