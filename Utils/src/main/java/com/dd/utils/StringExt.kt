package com.dd.utils

private val IS_CHINA_PHONE_NUMBER =
    Regex("^((13\\d)|(14[5,79])|(15[0-3,5-9])|(166)|(17\\d)|(18\\d)|(19[1,89]))\\d{8}$")

fun String.isPhoneNumber(): Boolean {
    if (this.isEmpty()) {
        return false
    }
    return IS_CHINA_PHONE_NUMBER.matches(this)
}