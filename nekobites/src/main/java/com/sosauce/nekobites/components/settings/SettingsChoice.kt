@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.sosauce.nekobites.components.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.sosauce.nekobites.R

data class SettingOption<T>(
    val value: T,
    @StringRes val labelRes: Int
)

object SettingsChoiceDefaults {
    @Composable
    fun colors(): ToggleButtonColors {
        val colors = MaterialTheme.colorScheme
        return ToggleButtonColors(
            containerColor = colors.surfaceContainerHighest,
            contentColor = colors.onSurface,
            disabledContainerColor = colors.onSurface.copy(alpha = 0.1f),
            disabledContentColor = colors.onSurface.copy(alpha = 0.38f),
            checkedContainerColor = colors.primary,
            checkedContentColor = colors.onPrimary
        )
    }
}

@Composable
fun <T> SettingsChoice(
    active: T,
    options: List<T>,
    onSelect: (T) -> Unit,
    title: @Composable () -> Unit,
    label: @Composable RowScope.(T) -> Unit,
    modifier: Modifier = Modifier,
    description: (@Composable () -> Unit)? = null,
    colors: ToggleButtonColors = SettingsChoiceDefaults.colors()
) {
    SettingsItem(modifier = modifier) {
        SettingsRow(title = title, description = description)
        Row(
            horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            options.fastForEachIndexed { index, option ->
                val selected = option == active
                ToggleButton(
                    checked = selected,
                    onCheckedChange = { onSelect(option) },
                    shapes = when (index) {
                        0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                        options.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                        else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                    },
                    colors = colors,
                    modifier = Modifier.weight(1f)
                ) {
                    if (selected) {
                        Icon(
                            painter = painterResource(R.drawable.check),
                            contentDescription = null
                        )
                        Spacer(Modifier.width(ButtonDefaults.IconSpacing))
                    }
                    label(option)
                }
            }
        }
    }
}

@Composable
fun <T> SettingsChoice(
    title: String,
    active: T,
    options: List<SettingOption<T>>,
    onSelect: (T) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null
) {
    SettingsChoice(
        active = active,
        options = options.map { it.value },
        onSelect = onSelect,
        title = { Text(title) },
        label = { value ->
            options.firstOrNull { it.value == value }?.let { Text(stringResource(it.labelRes)) }
        },
        modifier = modifier,
        description = settingsText(description)
    )
}
