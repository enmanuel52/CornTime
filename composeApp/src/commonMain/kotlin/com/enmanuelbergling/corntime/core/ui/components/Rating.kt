package com.enmanuelbergling.corntime.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.enmanuelbergling.corntime.core.ui.theme.Gold
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RatingStars(
    value: Float,
    modifier: Modifier = Modifier,
    size: Dp = 20.dp,
    spaceBetween: Dp = 2.dp,
) {
    Box(modifier) {
        Row(
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spaceBetween),
        ) {
            repeat(5) {
                Icon(
                    imageVector = Icons.Outlined.StarOutline,
                    tint = Gold,
                    contentDescription = "start",
                    modifier = Modifier.size(size)
                )
            }
        }

        Row(
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spaceBetween),
            modifier = Modifier.drawWithContent {
                clipRect(
                    right = value / 5 * this.size.width
                ) {
                    this@drawWithContent.drawContent()
                }
            }
        ) {
            repeat(5) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    tint = Gold,
                    contentDescription = "start",
                    modifier = Modifier.size(size)
                )
            }
        }
    }
}

@Preview
@Composable
fun RatingStarsPrev() {
    RatingStars(value = 3.4f, spaceBetween = 1.dp, size = 10.dp)
}