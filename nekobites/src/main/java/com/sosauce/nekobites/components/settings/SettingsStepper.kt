@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.sosauce.nekobites.components.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sosauce.nekobites.R
import com.sosauce.nekobites.animations.AnimatedCounter

@Composable
fun SettingsStepper(
    value: Int,
    range: IntRange,
    onValueChange: (Int) -> Unit,
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    description: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    step: Int = 1
) {
    SettingsItem(modifier = modifier) {
        SettingsRow(title = title, description = description, icon = icon) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                FilledTonalIconButton(
                    onClick = { onValueChange((value - step).coerceIn(range)) },
                    enabled = value > range.first,
                    shapes = IconButtonDefaults.shapes(),
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.remove),
                            contentDescription = null
                        )
                    }
                )
                Spacer(Modifier.width(5.dp))
                AnimatedCounter(
                    count = value,
                    textStyle = MaterialTheme.typography.titleLargeEmphasized.copy(textAlign = TextAlign.Center)
                )
                Spacer(Modifier.width(5.dp))
                FilledTonalIconButton(
                    onClick = { onValueChange((value + step).coerceIn(range)) },
                    enabled = value < range.last,
                    shapes = IconButtonDefaults.shapes(),
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.add),
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun SettingsStepper(
    title: String,
    value: Int,
    range: IntRange,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null
) {
    SettingsStepper(
        value = value,
        range = range,
        onValueChange = onValueChange,
        title = { Text(title) },
        modifier = modifier,
        description = settingsText(description)
    )
}
