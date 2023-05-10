package com.dd.basiccompose.ext

import androidx.paging.PagingSource
import androidx.paging.PagingState

fun <Item : Any> createIntPagingSource(fetch: suspend (Int) -> List<Item>): PagingSource<Int, Item> {
    return IntPagingSource(fetch)
}

private class IntPagingSource<Item : Any>(val fetch: suspend (Int) -> List<Item>) :
    PagingSource<Int, Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val pageIndex = params.key ?: 1
        val dataList = fetch(pageIndex)
        return try {
            val prevKey = if (pageIndex == 1) null else pageIndex.minus(1)
            val nextKey =
                if (dataList.isEmpty()) null else pageIndex.plus(1)
            LoadResult.Page(dataList, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}