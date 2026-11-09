package com.sosauce.nekobites.components.settings

import androidx.annotation.DrawableRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun SettingsClickable(
    onClick: () -> Unit,
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    description: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    baseShape: Shape? = null
) {
    SettingsItem(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        baseShape = baseShape
    ) {
        SettingsRow(
            title = title,
            description = description,
            icon = icon,
            trailing = trailing
        )
    }
}

@Composable
fun SettingsClickable(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    description: String? = null,
    @DrawableRes icon: Int? = null,
    trailing: (@Composable () -> Unit)? = null,
    baseShape: Shape? = null
) {
    SettingsClickable(
        onClick = onClick,
        title = { Text(title) },
        modifier = modifier,
        enabled = enabled,
        description = settingsText(description),
        icon = settingsIcon(icon),
        trailing = trailing,
        baseShape = baseShape
    )
}
