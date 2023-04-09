package com.dd.basiccompose.theme.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.dd.basiccompose.theme.ThemeRoute

const val themeNavigationRoute = "theme_route"

fun NavController.navigateToTheme(navOptions: NavOptions? = null) {
    this.navigate(themeNavigationRoute, navOptions)
}

fun NavGraphBuilder.themeScreen() {
    composable(route = themeNavigationRoute) {
        ThemeRoute()
    }
}

