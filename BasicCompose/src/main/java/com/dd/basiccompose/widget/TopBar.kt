package com.dd.basiccompose.widget

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.dd.basiccompose.navigation.LocalNavController

@Composable
fun TopBarNavBack(
    title: String,
    modifier: Modifier,
    imageVector1: ImageVector? = null,
    imageVector2: ImageVector? = null,
    onClickImageVector1: (() -> Unit) = {},
    onClickImageVector2: (() -> Unit) = {},
    navCtrl: NavHostController = LocalNavController.current,
) {
    TopAppBar(
        title = {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = {
                navCtrl.navigateUp()
            }) {
                Icon(Icons.Filled.ArrowBack, null, tint = MaterialTheme.colorScheme.onSurface)
            }
        },
        actions = {
            imageVector1?.let {
                IconButton(onClick = { onClickImageVector1.invoke() }) {
                    Icon(imageVector1, null, tint = MaterialTheme.colorScheme.onSurface)
                }
            }
            imageVector2?.let {
                IconButton(onClick = { onClickImageVector2.invoke() }) {
                    Icon(imageVector2, null, tint = MaterialTheme.colorScheme.onSurface)
                }
            }
        },
    )
}

