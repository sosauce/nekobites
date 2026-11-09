package com.sosauce.nekobites.components.settings

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
private fun ScrollPage(
    contentPadding: PaddingValues,
    content: @Composable ColumnScope.() -> Unit
) {
    SettingsPage(
        contentPadding = contentPadding,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        content = content
    )
}