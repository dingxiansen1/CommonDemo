package com.dd.basiccompose.theme

import android.os.Build
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.dd.basiccompose.R
import com.dd.basiccompose.controller.LocalNavController

@Composable
internal fun ThemeRoute() {
    ThemeScreen()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ThemeScreen(
    nav: NavHostController = LocalNavController.current
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        Column {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.appearance_setting)) },
                navigationIcon = {
                    IconButton(onClick = {
                        nav.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            Icons.Filled.ArrowBack.name
                        )
                    }
                },
                scrollBehavior = scrollBehavior

            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = stringResource(id = R.string.dark_mode),
                    color = MaterialTheme.colorScheme.primary
                )


                DarkModeItem()

                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = stringResource(id = R.string.theme),
                    color = MaterialTheme.colorScheme.primary
                )

                ThemeModeItem()
            }

        }
    }
}


@Composable
internal fun DarkModeItem() {
    val theme by ThemeUtils.appTheme.collectAsStateWithLifecycle()
    val list = listOf(
        Triple(Icons.Filled.Android, stringResource(R.string.dark_auto), DarkMode.Auto),
        Triple(Icons.Filled.WbSunny, stringResource(R.string.dark_off), DarkMode.Light),
        Triple(Icons.Filled.NightsStay, stringResource(R.string.dark_on), DarkMode.Dark)
    )

    val enableColor = MaterialTheme.colorScheme.primary
    val disableColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp)
    ) {
        list.forEachIndexed { index, (image, text, mode) ->
            val currentColor = if (theme.darkMode == mode) enableColor else disableColor
            Column(
                Modifier
                    .weight(1f)
                    .padding(horizontal = 6.dp)
                    .border(
                        width = 1.dp,
                        color = currentColor,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clip(RoundedCornerShape(6.dp))
                    .clickable {
                        if (theme.darkMode != mode) {
                            ThemeUtils.changeDarkMode(mode)
                        }
                    }
                    .padding(12.dp)
            ) {
                Icon(
                    imageVector = image,
                    contentDescription = text,
                    tint = currentColor,
                    modifier = Modifier.padding(end = 12.dp)
                )
                Text(text = text, color = currentColor)
            }
        }
    }


}

//val lazyListState = LazyListState()

@Composable
internal fun ThemeModeItem() {
    val theme by ThemeUtils.appTheme.collectAsStateWithLifecycle()
    val isDark = when (theme.darkMode) {
        DarkMode.Dark -> true
        DarkMode.Light -> false
        DarkMode.Auto -> isSystemInDarkTheme()
    }

    val context = LocalContext.current


    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(start = 6.dp, end = 6.dp)
    ) {
        if (ThemeUtils.isSupportDynamicColor() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val dynamicColor =
                if (isDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            item {
                ThemePreviewItem(
                    selected = theme.isDynamicColor,
                    colorScheme = dynamicColor,
                    stringResource(id = R.string.is_dynamic_color)
                ) {
                    if (!theme.isDynamicColor) {
                        ThemeUtils.changeIsDynamicColor(true)
                    }

                }
            }
        }
        items(ThemeMode.values()) {
            ThemePreviewItem(
                selected = !theme.isDynamicColor && theme.themeMode == it,
                colorScheme = if (isDark) it.darkColorScheme else it.lightColorScheme,
                stringResource(id = it.titleResId)
            ) {
                ThemeUtils.changeThemeMode(it)
            }

        }
    }
}


@Composable
internal fun ThemePreviewItem(
    selected: Boolean,
    colorScheme: ColorScheme,
    title: String,
    onClick: () -> Unit,
) {
    val dividerColor = colorScheme.onSurface.copy(alpha = 0.2f)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .width(120.dp)
                .aspectRatio(9f / 16f)
                .border(
                    width = 4.dp,
                    color = if (selected) {
                        colorScheme.primary
                    } else {
                        dividerColor
                    },
                    shape = RoundedCornerShape(17.dp),
                )
                .padding(4.dp)
                .clip(RoundedCornerShape(13.dp))
                .background(colorScheme.background)
                .clickable(onClick = onClick),
        ) {
            // App Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                        .weight(0.7f)
                        .padding(end = 4.dp)
                        .background(
                            color = colorScheme.onSurface,
                            shape = MaterialTheme.shapes.small,
                        ),
                )

                Box(
                    modifier = Modifier.weight(0.3f),
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    if (selected) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = stringResource(R.string.theme),
                            tint = colorScheme.primary,
                        )
                    }
                }
            }

            // Cover
            Box(
                modifier = Modifier
                    .padding(start = 8.dp, top = 2.dp)
                    .background(
                        color = dividerColor,
                        shape = MaterialTheme.shapes.small,
                    )
                    .fillMaxWidth(0.5f)
                    .aspectRatio(19 / 27F),
            ) {
                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(width = 24.dp, height = 16.dp)
                        .clip(RoundedCornerShape(5.dp)),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(12.dp)
                            .background(colorScheme.tertiary),
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(12.dp)
                            .background(colorScheme.secondary),
                    )
                }
            }

            // Bottom bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Surface(
                    tonalElevation = 3.dp,
                ) {
                    Row(
                        modifier = Modifier
                            .height(32.dp)
                            .fillMaxWidth()
                            .background(colorScheme.surfaceVariant)
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(17.dp)
                                .background(
                                    color = colorScheme.primary,
                                    shape = CircleShape,
                                ),
                        )
                        Box(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .alpha(0.6f)
                                .height(17.dp)
                                .weight(1f)
                                .background(
                                    color = colorScheme.onSurface,
                                    shape = MaterialTheme.shapes.small,
                                ),
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.size(8.dp))
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
    }

}
