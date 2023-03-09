package com.dd.basiccompose.widget

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dd.basiccompose.controller.LocalNavController

@SuppressLint("ModifierParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBarBack(
    title: String,
    modifier: Modifier = Modifier,
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
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.background),
        navigationIcon = {
            IconButton(

                onClick = {
                    navCtrl.navigateUp()
                }) {
                Icon(Icons.Filled.ArrowBack, null, tint = MaterialTheme.colorScheme.onBackground)
            }
        },
        actions = {
            imageVector1?.let {
                IconButton(onClick = { onClickImageVector1.invoke() }) {
                    Icon(imageVector1, null, tint = MaterialTheme.colorScheme.onBackground)
                }
            }
            imageVector2?.let {
                IconButton(onClick = { onClickImageVector2.invoke() }) {
                    Icon(imageVector2, null, tint = MaterialTheme.colorScheme.onBackground)
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    title: String,
    modifier: Modifier = Modifier,
    leftIcon: ImageVector? = null,
    leftClick: (() -> Unit) = {},
    imageVector1: ImageVector? = null,
    imageVector2: ImageVector? = null,
    onClickImageVector1: (() -> Unit) = {},
    onClickImageVector2: (() -> Unit) = {},
) {
    TopAppBar(
        title = {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
        },
        modifier = modifier,
        navigationIcon = {
            leftIcon?.let {
                IconButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 20.dp),
                    onClick = {
                        leftClick.invoke()
                    }) {
                    Icon(leftIcon, null, tint = MaterialTheme.colorScheme.onBackground)
                }
            }
        },
        actions = {
            imageVector1?.let {
                IconButton(onClick = { onClickImageVector1.invoke() }) {
                    Icon(imageVector1, null, tint = MaterialTheme.colorScheme.onBackground)
                }
            }
            imageVector2?.let {
                IconButton(onClick = { onClickImageVector2.invoke() }) {
                    Icon(imageVector2, null, tint = MaterialTheme.colorScheme.onBackground)
                }
            }
        },
    )
}
