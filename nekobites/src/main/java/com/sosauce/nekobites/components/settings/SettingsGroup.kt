@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.sosauce.nekobites.components.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun SettingsPage(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(bottom = contentPadding.calculateBottomPadding() + 16.dp),
        content = content
    )
}

@Composable
fun SettingsGroupLabel(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    trailing: (@Composable () -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 28.dp, end = 20.dp, top = 20.dp, bottom = 10.dp)
    ) {
        Box(Modifier.weight(1f)) {
            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.primary) {
                ProvideTextStyle(MaterialTheme.typography.titleSmallEmphasized, title)
            }
        }
        trailing?.invoke()
    }
}

@Composable
fun SettingsGroupLabel(
    text: String,
    modifier: Modifier = Modifier,
    trailing: (@Composable () -> Unit)? = null
) {
    SettingsGroupLabel(
        title = { Text(text) },
        modifier = modifier,
        trailing = trailing
    )
}

@Composable
fun SettingsGroup(
    modifier: Modifier = Modifier,
    title: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier.fillMaxWidth()) {
        if (title != null) SettingsGroupLabel(title) else Spacer(Modifier.height(16.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(SettingsDefaults.GroupGap),
            modifier = Modifier
                .padding(horizontal = SettingsDefaults.GroupHorizontalPadding)
                .clip(RoundedCornerShape(SettingsDefaults.GroupCorner)),
            content = content
        )
    }
}

@Composable
fun SettingsGroup(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    SettingsGroup(
        modifier = modifier,
        title = { Text(title) },
        content = content
    )
}

fun settingsItemShape(isFirst: Boolean, isLast: Boolean): Shape =
    SettingsDefaults.itemShape(isFirst, isLast)
