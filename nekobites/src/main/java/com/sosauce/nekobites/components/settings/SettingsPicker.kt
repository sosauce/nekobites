package com.sosauce.nekobites.components.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SettingsPicker(
    modifier: Modifier = Modifier,
    title: (@Composable () -> Unit)? = null,
    description: (@Composable () -> Unit)? = null,
    horizontalSpacing: Dp = 4.dp,
    tiles: LazyListScope.() -> Unit
) {
    SettingsItem(modifier = modifier) {
        if (title != null) SettingsRow(title = title, description = description)
        LazyRow(
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp,
                top = if (title == null) 12.dp else 0.dp,
                bottom = 12.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
            content = tiles
        )
    }
}

@Composable
fun SettingsPicker(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    tiles: LazyListScope.() -> Unit
) {
    SettingsPicker(
        modifier = modifier,
        title = { Text(title) },
        description = settingsText(description),
        tiles = tiles
    )
}
