package com.sosauce.nekobites.components.settings

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SettingsIconBadge(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    shape: Shape = MaterialShapes.Cookie9Sided.toShape(),
    content: @Composable () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(SettingsDefaults.IconBadgeSize)
            .background(containerColor, shape)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(SettingsDefaults.IconSize)
        ) {
            CompositionLocalProvider(LocalContentColor provides contentColor, content = content)
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SettingsIconBadge(
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    shape: Shape = MaterialShapes.Cookie9Sided.toShape()
) {
    SettingsIconBadge(
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        shape = shape
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null
        )
    }
}
