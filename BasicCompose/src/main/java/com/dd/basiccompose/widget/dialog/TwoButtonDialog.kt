package com.dd.basiccompose.widget.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

/**
 * 适用于需要自定义正文的确定弹窗
 * 如：带不同颜色或带有超链接的正文
 **/
@Composable
fun TwoButtonDialog(
    onDismissRequest: (() -> Unit) = {},
    icon: @Composable (() -> Unit)? = null,
    title: String = "",
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge,
    confirm: String = "确定",
    onConfirmClick: (() -> Unit) = {},
    cancel: String = "取消",
    onCancelClick: (() -> Unit) = {},
    text: @Composable (() -> Unit) = { },
) {
    AlertDialog(
        onDismissRequest = {
            onDismissRequest.invoke()
        },
        icon = {
            icon?.invoke()
        },
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = title, style = titleStyle)
            }
        },
        text = {
            text.invoke()
        },
        confirmButton = {
            Button(
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                onClick = { onConfirmClick.invoke() }
            ) {
                Text(
                    text = confirm,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        dismissButton = {
            Button(
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                onClick = { onCancelClick.invoke() }
            ) {
                Text(
                    text = cancel,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    )

}


/**
 * 适用于不需要自定义正文的确定弹窗
 **/
@Composable
fun TwoButtonDialog(
    onDismissRequest: (() -> Unit) = {},
    icon: @Composable (() -> Unit)? = null,
    title: String = "",
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge,
    text: String = "",
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    cancel: String = "取消",
    onCancelClick: (() -> Unit) = {},
    confirm: String = "确定",
    onConfirmClick: (() -> Unit) = {},
) {
    AlertDialog(
        onDismissRequest = {
            onDismissRequest.invoke()
        },
        icon = {
            icon?.invoke()
        },
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = title, style = titleStyle)
            }
        },
        text = {
            Text(
                text = text,
                style = textStyle
            )
        },
        confirmButton = {
            Button(
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                onClick = { onConfirmClick.invoke() }
            ) {
                Text(
                    text = confirm,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        dismissButton = {
            Button(
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                onClick = { onCancelClick.invoke() }
            ) {
                Text(
                    text = cancel,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    )

}


@Preview
@Composable
fun TwoButtonDialogPreview() {
    TwoButtonDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.Collections,
                contentDescription = Icons.Default.Collections.name
            )
        },
        title = "AlertDialog",
        text = {
            Text(
                text = "AlertDialog 将根据可用空间来定位其按钮。" +
                        "默认情况下，它将尝试将它们水平地放在彼此的旁边，" +
                        "如果没有足够的空间，则退回到水平放置。还有另一个" +
                        "版本的 Composable，它有一个按钮槽，可以提供自" +
                        "定义的按钮布局"
            )
        },
    )
}