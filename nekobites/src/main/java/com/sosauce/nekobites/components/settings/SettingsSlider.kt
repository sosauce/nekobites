@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package com.sosauce.nekobites.components.settings

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object SettingsSliderDefaults {
    @Composable
    fun Value(text: String) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLargeEmphasized,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }
    }

    @Composable
    fun DefaultSlider(state: SliderState, onValueChange: (Float) -> Unit) {
        Slider(state = state, onValueChange = onValueChange)
    }
}

@Composable
fun SettingsSlider(
    value: Int,
    onValueChange: (Int) -> Unit,
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..60f,
    description: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    valueContent: @Composable (Int) -> Unit = { SettingsSliderDefaults.Value(it.toString()) },
    slider: @Composable (state: SliderState, onValueChange: (Float) -> Unit) -> Unit = { state, onChange ->
        SettingsSliderDefaults.DefaultSlider(state, onChange)
    }
) {
    val animatedValue by animateIntAsState(value, label = "sliderValue")
    val sliderState = rememberSliderState(
        value = value.toFloat(),
        trackRange = valueRange
    )

    LaunchedEffect(animatedValue) {
        sliderState.value = animatedValue.toFloat()
    }

    SettingsItem(modifier = modifier) {
        SettingsRow(
            title = title,
            description = description,
            icon = icon,
            trailing = { valueContent(animatedValue) }
        )
        Box(Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp)) {
            slider(sliderState) { onValueChange(it.toInt()) }
        }
    }
}

@Composable
fun SettingsSlider(
    title: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    unit: String? = null,
    valueRange: ClosedFloatingPointRange<Float> = 0f..60f,
    description: String? = null,
    slider: @Composable (state: SliderState, onValueChange: (Float) -> Unit) -> Unit = { state, onChange ->
        SettingsSliderDefaults.DefaultSlider(state, onChange)
    }
) {
    SettingsSlider(
        value = value,
        onValueChange = onValueChange,
        title = { Text(title) },
        modifier = modifier,
        valueRange = valueRange,
        description = settingsText(description),
        valueContent = { SettingsSliderDefaults.Value(if (unit != null) "$it$unit" else "$it") },
        slider = slider
    )
}
