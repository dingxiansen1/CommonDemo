package com.dd.basiccompose.theme

import com.dd.common.utils.DataStoreUtils
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
