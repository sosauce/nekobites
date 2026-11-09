@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.sosauce.nekobites.components.settings

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SelectableTile(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: (@Composable () -> Unit)? = null,
    width: Dp = 64.dp,
    height: Dp = 64.dp,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerHighest,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    labelColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    content: @Composable BoxScope.() -> Unit
) {
    val spec = MaterialTheme.motionScheme.defaultSpatialSpec<Dp>()
    val corner by animateDpAsState(if (selected) height / 2 else 18.dp, spec)
    val ringColor by animateColorAsState(
        if (selected) selectedColor else Color.Transparent
    )
    val animatedLabelColor by animateColorAsState(
        if (selected) selectedColor else labelColor
    )
    val ringShape = RoundedCornerShape(corner + 5.dp)
    val shape = RoundedCornerShape(corner)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(6.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .border(2.5.dp, ringColor, ringShape)
                .padding(5.dp)
                .size(width = width, height = height)
                .clip(shape)
                .background(containerColor),
            content = content
        )
        if (label != null) {
            Spacer(Modifier.height(6.dp))
            CompositionLocalProvider(LocalContentColor provides animatedLabelColor) {
                ProvideTextStyle(
                    value = if (selected) MaterialTheme.typography.labelLargeEmphasized
                    else MaterialTheme.typography.labelLargeEmphasized,
                    content = label
                )
            }
        }
    }
}

@Composable
fun SelectableTile(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    width: Dp = 64.dp,
    height: Dp = 64.dp,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerHighest,
    content: @Composable BoxScope.() -> Unit
) {
    SelectableTile(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        label = { Text(text = label, maxLines = 1) },
        width = width,
        height = height,
        containerColor = containerColor,
        content = content
    )
}
