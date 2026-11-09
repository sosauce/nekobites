package com.sosauce.nekobites.components.settings

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object SettingsDefaults {
    val GroupCorner: Dp = 24.dp
    val ItemCorner: Dp = 2.dp
    val ItemPressedCorner: Dp = 22.dp
    val GroupGap: Dp = 2.dp
    val GroupHorizontalPadding: Dp = 16.dp
    val IconBadgeSize: Dp = 42.dp
    val IconSize: Dp = 22.dp

    val containerColor: Color
        @Composable @ReadOnlyComposable get() = MaterialTheme.colorScheme.surfaceContainer

    fun itemShape(isFirst: Boolean, isLast: Boolean): Shape = RoundedCornerShape(
        topStart = if (isFirst) GroupCorner else ItemCorner,
        topEnd = if (isFirst) GroupCorner else ItemCorner,
        bottomStart = if (isLast) GroupCorner else ItemCorner,
        bottomEnd = if (isLast) GroupCorner else ItemCorner
    )
}
