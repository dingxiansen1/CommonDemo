package com.dd.basiccompose.ext

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

fun Modifier.clickableRipple(
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified,
    onClick: () -> Unit
): Modifier = composed {
    clickable(
        indication = rememberRipple(bounded, radius, color),
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}

fun Modifier.clickableNoRipple(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}