package com.dd.basiccompose.widget.item

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Cached
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SettingItemPreview() {
    Column {
        SettingItemWithData(
            title = "清理缓存",
            icon = Icons.Outlined.Cached,
            data = "130MB"
        ) {

        }
        Spacer(modifier = Modifier.height(16.dp))
        SettingItem(
            title = "清理缓存",
            icon = Icons.Outlined.Cached,
            description = "当前缓存为:130MB"
        ) {

        }
        Spacer(modifier = Modifier.height(16.dp))

        val list = arrayListOf("家", "公司")
        var firstItem by remember {
            mutableStateOf(list[0])
        }
        var show by remember {
            mutableStateOf(false)
        }
        SettingItemDropDownMenu(
            state = show,
            title = "地址",
            curItem = firstItem,
            open = { show = true },
            close = { show = false },
            items = list,
            itemText = {
                it.toString()
            }
        ) {
            firstItem = list[it]
        }
    }

}

@SuppressLint("ModifierParameter")
@Composable
fun SettingItemDropDownMenu(
    state: Boolean,
    open: () -> Unit,
    close: () -> Unit,
    title: String,
    icon: ImageVector? = null,
    curItem: String,
    items: List<Any>,
    itemText: (Any) -> String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 20.dp),
    onClick: (Int) -> Unit,
) {
    Surface(
        modifier = if (items.isNotEmpty()) Modifier.clickable { open.invoke() } else Modifier
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                icon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = it.name,
                        modifier = Modifier.padding(end = 16.dp),
                    )
                }
                Text(
                    text = title,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (items.isNotEmpty()) {
                    DropdownMenu(
                        expanded = state,
                        offset = DpOffset(0.dp, 0.dp),
                        onDismissRequest = close::invoke,
                    ) {
                        items.forEachIndexed { index, item ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = itemText.invoke(item),
                                        style = MaterialTheme.typography.titleMedium,
                                    )
                                },
                                onClick = {
                                    close.invoke()
                                    onClick.invoke(index)
                                },
                            )
                        }
                    }
                }

                Text(
                    text = curItem,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(end = 16.dp),
                )
                Icon(
                    imageVector = Icons.Outlined.ArrowDropDown,
                    contentDescription = Icons.Outlined.ArrowDropDown.name,
                )
            }
        }
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun SettingItemWithData(
    title: String,
    icon: ImageVector? = null,
    data: String = "",
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 20.dp),
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable { onClick() }
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                icon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = it.name,
                        modifier = Modifier.padding(end = 16.dp),
                    )
                }
                Text(
                    text = title,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = data,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(end = 16.dp).fillMaxWidth(0.6f),
                )
                Icon(
                    imageVector = Icons.Outlined.ArrowForwardIos,
                    contentDescription = Icons.Outlined.ArrowForwardIos.name,
                )
            }
        }
    }
}


@SuppressLint("ModifierParameter")
@Composable
fun SettingItem(
    title: String,
    description: String = "",
    icon: ImageVector? = null,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 20.dp),
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable { onClick() }
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = title,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .size(25.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 0.dp)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 5,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}


@Composable
fun CreditItem(
    title: String,
    description: String? = null,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Surface(
        modifier = if (enabled) Modifier.clickable { onClick() } else Modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            ) {
                with(MaterialTheme) {
                    Text(
                        text = title,
                        maxLines = 1,
                        style = typography.titleMedium,
                        color = colorScheme.onSurface
                    )
                    Text(
                        text = description.toString(),
                        color = colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = typography.bodyMedium,
                    )
                }
            }
        }
    }

}
