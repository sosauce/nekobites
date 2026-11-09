package com.sosauce.nekobites.components.settings

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sosauce.nekobites.R
import com.sosauce.nekobites.components.Spacer
import com.sosauce.nekobites.utils.SUPPORT_PAGE
import com.sosauce.nekobites.utils.appVersion

@Composable
fun AboutCard(
    @DrawableRes icon: Int,
    appName: String,
    updateLink: String
) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val colors = MaterialTheme.colorScheme

    Surface(
        shape = MaterialTheme.shapes.extraLarge,
        color = colors.surfaceContainer,
        contentColor = colors.onSurface,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(15.dp)
                        .background(
                            shape = RoundedCornerShape(24.dp),
                            color = Color(0xFFf4a7bd)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp),
                        tint = Color(0xFFfdd9dc)
                    )
                }
                Spacer(5.dp)
                Column {
                    Text(
                        text = appName,
                        style = MaterialTheme.typography.headlineMediumEmphasized
                    )
                    Spacer(Modifier.height(6.dp))
                    Surface(
                        shape = CircleShape,
                        color = colors.onSurface.copy(alpha = 0.1f),
                        contentColor = colors.onSurface
                    ) {
                        Text(
                            text = "v${context.appVersion}",
                            style = MaterialTheme.typography.labelLargeEmphasized,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
                FilledIconButton(
                    onClick = { uriHandler.openUri(updateLink) },
                    shapes = IconButtonDefaults.shapes()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.github),
                        contentDescription = null
                    )
                }

                FilledIconButton(
                    onClick = { uriHandler.openUri(SUPPORT_PAGE) },
                    shapes = IconButtonDefaults.shapes()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.favorite_filled),
                        contentDescription = null
                    )
                }
            }
        }
    }
}