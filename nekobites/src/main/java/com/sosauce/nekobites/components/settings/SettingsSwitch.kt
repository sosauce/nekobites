package com.sosauce.nekobites.components.settings

import androidx.annotation.DrawableRes
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun SettingsSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    description: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    baseShape: Shape? = null,
    colors: SwitchColors = SwitchDefaults.colors(uncheckedBorderColor = Color.Transparent)
) {
    SettingsItem(
        modifier = modifier,
        onClick = { onCheckedChange(!checked) },
        enabled = enabled,
        baseShape = baseShape
    ) {
        SettingsRow(
            title = title,
            description = description,
            icon = icon
        ) {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
                colors = colors
            )
        }
    }
}

@Composable
fun SettingsSwitch(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    description: String? = null,
    @DrawableRes icon: Int? = null,
    baseShape: Shape? = null
) {
    SettingsSwitch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        title = { Text(title) },
        modifier = modifier,
        enabled = enabled,
        description = settingsText(description),
        icon = settingsIcon(icon),
        baseShape = baseShape
    )
}
