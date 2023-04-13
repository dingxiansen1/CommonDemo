package com.dd.basiccompose.widget

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.dd.basiccompose.controller.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ModifierParameter")
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
        navigationIcon = Icons.Filled.ArrowBackIos,
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
    colors: TopAppBarColors = TopAppBarDefaults.smallTopAppBarColors(),
    actions: @Composable RowScope.() -> Unit = {},
    leftClick: (() -> Unit) = {},
) {
    TopAppBar(
        title = {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
        },
        colors = colors,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    text: String,
    hint: String = "",
    navigateIcon: ImageVector? = null,
    navigateClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    onTextChanged: ((String) -> Unit),
) {
    CenterAlignedTopAppBar(
        title = {
            SearchBar(text = text, hint = hint, onTextChanged = onTextChanged)
        },
        navigationIcon = {
            navigateIcon?.let {
                IconButton(onClick = {
                    navigateClick.invoke()
                }) {
                    Icon(
                        imageVector = navigateIcon,
                        contentDescription = navigateIcon.name,
                    )
                }
            }
        },
        actions = {
            actions.invoke(this)
        }
    )
}
