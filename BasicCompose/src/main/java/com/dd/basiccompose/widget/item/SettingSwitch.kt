package com.dd.basiccompose.widget.item

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd.basiccompose.widget.Switch

@Preview
@Composable
fun SettingSwitchPreview() {
    SettingSwitch(
        title = "标题",
        description = "描述：这是一个通用的带有switch开关的item",
        icon = Icons.Outlined.Palette,
    ) {}
}

@SuppressLint("ModifierParameter")
@Composable
fun SettingSwitch(
    title: String = "",
    description: String? = null,
    icon: ImageVector? = null,
    iconTint: Color = MaterialTheme.colorScheme.primary,
    enabled: Boolean = true,
    isChecked: Boolean = true,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 20.dp),
    onClick: () -> Unit = {},
) {
    Surface(modifier = if (enabled) Modifier.clickable { onClick() } else Modifier) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon?.let {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .size(25.dp),
                    tint = iconTint
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis
                )
                if (!description.isNullOrEmpty()) Text(
                    text = description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 5,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Switch(
                checked = isChecked,
                modifier = Modifier.padding(start = 20.dp, end = 6.dp),
                enable = enabled
            )

        }
    }
}