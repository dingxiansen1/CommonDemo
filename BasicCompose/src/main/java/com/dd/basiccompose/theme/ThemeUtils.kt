package com.dd.basiccompose.theme

import android.content.res.Configuration
import android.os.Build
import com.dd.common.utils.DataStoreUtils
import com.dd.utils.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

object ThemeUtils {

    private const val IS_FOLLOW_THE_SYSTEM = "is_follow_the_system"

    private const val IS_NIGHT_MODEL = "is_night_model"


    val mAppTheme = MutableStateFlow(
        AppTheme(
            DataStoreUtils.getSyncData(IS_FOLLOW_THE_SYSTEM, false),
            DataStoreUtils.getSyncData(IS_NIGHT_MODEL, false)
        )
    )

    fun isNightTheme(): Boolean {
        if (mAppTheme.value.mIsFollowTheSystem){
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Utils.getApp().resources.configuration.isNightModeActive
            } else {
                Utils.getApp().resources.configuration.uiMode == Configuration.UI_MODE_NIGHT_YES
            }
        }
        if (mAppTheme.value.mIsNightModel){
            return true
        }
        return false
    }

    /**
     * 设置当前主题相反的主题
     * 注意:如果当前模式是跟随系统
     * 那么改状态会丢失
     **/
    fun setUnCurTheme(){
        setNightModel(!isNightTheme())
    }

    fun setFollowTheSystem(value: Boolean) {
        mAppTheme.update {
            it.copy(mIsFollowTheSystem = value, mIsNightModel = false)
        }
        DataStoreUtils.putSyncData(IS_FOLLOW_THE_SYSTEM, value)
    }

    fun setNightModel(value: Boolean) {
        mAppTheme.update {
            it.copy(mIsNightModel = value, mIsFollowTheSystem = false)
        }
        DataStoreUtils.putSyncData(IS_NIGHT_MODEL, value)
    }

}

data class AppTheme(
    val mIsFollowTheSystem: Boolean,
    val mIsNightModel: Boolean,
)
