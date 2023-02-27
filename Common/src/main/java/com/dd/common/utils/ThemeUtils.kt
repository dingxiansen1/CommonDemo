package com.dd.common.utils

object ThemeUtils {

    private const val IS_FOLLOW_THE_SYSTEM = "is_follow_the_system"

    private const val IS_NIGHT_MODEL = "is_night_model"


    var mIsFollowTheSystem: Boolean
        get() = DataStoreUtils.getSyncData(IS_FOLLOW_THE_SYSTEM, false)
        set(value) {
            DataStoreUtils.putSyncData(IS_FOLLOW_THE_SYSTEM, value)
        }


    var mIsNightModel: Boolean
        get() = DataStoreUtils.getSyncData(IS_NIGHT_MODEL, false)
        set(value) {
            DataStoreUtils.putSyncData(IS_NIGHT_MODEL, value)
        }
}
