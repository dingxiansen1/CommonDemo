package com.dd.basiccompose.theme

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.Keep
import com.dd.common.utils.CoroutineUtils
import com.dd.utils.Utils
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

enum class DarkMode {
    Light, Dark, Auto
}

@Keep
@Serializable
data class ThemeState(
    val darkMode: DarkMode = DarkMode.Auto,
    val themeMode: ThemeMode = ThemeMode.Default,
    val isDynamicColor: Boolean = true,
)

object ThemeUtils {

    private val scope = CoroutineUtils.getAppCoroutine()

    val appTheme: StateFlow<ThemeState> = Utils.getApp().themeState.data
        .stateIn(
            scope,
            SharingStarted.WhileSubscribed(5_000),
            ThemeState()
        )

    fun getDarkMode(): DarkMode {
        return appTheme.value.darkMode
    }

    fun isDarkMode(): Boolean {
        if (appTheme.value.darkMode == DarkMode.Auto) {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Utils.getApp().resources.configuration.isNightModeActive
            } else {
                Utils.getApp().resources.configuration.uiMode == Configuration.UI_MODE_NIGHT_YES
            }
        }
        if (appTheme.value.darkMode == DarkMode.Dark) {
            return true
        }
        return false
    }

    fun changeDarkMode(value: DarkMode) {
        scope.launch {
            Utils.getApp().themeState.updateData {
                appTheme.value.copy(darkMode = value)
            }
        }
    }

    fun changeThemeMode(value: ThemeMode) {
        scope.launch {
            Utils.getApp().themeState.updateData {
                appTheme.value.copy(themeMode = value)
            }
        }
    }

    fun changeIsDynamicColor(value: Boolean) {
        scope.launch {
            Utils.getApp().themeState.updateData {
                appTheme.value.copy(isDynamicColor = value)
            }
        }
    }

    fun isSupportDynamicColor(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    }

}

