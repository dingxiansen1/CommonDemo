package com.dd.basiccompose.widget

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.dd.basiccompose.controller.LocalNavController

@SuppressLint("ModifierParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBarBack(
    title: String,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    navCtrl: NavHostController = LocalNavController.current,
    onClick: () -> Unit = {},
) {
    DefaultTopBar(
        title = title,
        modifier = modifier,
        navigationIcon = Icons.Filled.ArrowBack,
        actions = actions,
        leftClick = {
            onClick.invoke()
            navCtrl.navigateUp()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: ImageVector? = null,
    actions: @Composable RowScope.() -> Unit = {},
    leftClick: (() -> Unit) = {},
) {
    TopAppBar(
        title = {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
        },
        modifier = modifier,
        navigationIcon = {
            navigationIcon?.let {
                IconButton(
                    onClick = {
                        leftClick.invoke()
                    }) {
                    Icon(
                        navigationIcon,
                        navigationIcon.name,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        actions = {
            actions.invoke(this)
        },
    )
}
