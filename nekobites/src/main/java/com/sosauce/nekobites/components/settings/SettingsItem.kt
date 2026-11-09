@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.sosauce.nekobites.components.settings

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    baseShape: Shape? = null,
    containerColor: Color = SettingsDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    content: @Composable ColumnScope.() -> Unit
) {
    if (onClick != null) {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        val corner by animateDpAsState(
            targetValue = if (isPressed) SettingsDefaults.ItemPressedCorner else SettingsDefaults.ItemCorner,
            animationSpec = MaterialTheme.motionScheme.fastSpatialSpec(),
            label = "itemCorner"
        )
        Surface(
            onClick = onClick,
            enabled = enabled,
            shape = if (isPressed || baseShape == null) RoundedCornerShape(corner) else baseShape,
            color = containerColor,
            contentColor = contentColor,
            interactionSource = interactionSource,
            modifier = modifier.fillMaxWidth()
        ) { Column(content = content) }
    } else {
        Surface(
            shape = baseShape ?: RoundedCornerShape(SettingsDefaults.ItemCorner),
            color = containerColor,
            contentColor = contentColor,
            modifier = modifier.fillMaxWidth()
        ) { Column(content = content) }
    }
}

@Composable
fun SettingsRow(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    description: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        icon?.let {
            it()
            Spacer(Modifier.width(16.dp))
        }
        Column(Modifier.weight(1f)) {
            ProvideTextStyle(MaterialTheme.typography.titleMediumEmphasized, title)
            description?.let {
                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                    ProvideTextStyle(MaterialTheme.typography.bodyMedium, it)
                }
            }
        }
        trailing?.let {
            Spacer(Modifier.width(12.dp))
            Box { it() }
        }
    }
}

@Composable
fun SettingsRow(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    @DrawableRes icon: Int? = null,
    trailing: (@Composable () -> Unit)? = null
) {
    SettingsRow(
        title = { Text(title) },
        modifier = modifier,
        description = settingsText(description),
        icon = settingsIcon(icon),
        trailing = trailing
    )
}

internal fun settingsText(text: String?): (@Composable () -> Unit)? =
    if (text != null) { { Text(text) } } else null

internal fun settingsIcon(@DrawableRes icon: Int?): (@Composable () -> Unit)? =
    if (icon != null) { { SettingsIconBadge(icon = icon) } } else null
