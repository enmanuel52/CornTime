package com.enmanuelbergling.corntime.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RatingStars(
    value: Float,
    modifier: Modifier = Modifier,
    size: Dp = 20.dp,
    spaceBetween: Dp = 2.dp,
) {
    TODO("implement a rating system")
    /*RatingBar(
        value = value,
        onValueChange = {},
        onRatingChanged = {},
        painterEmpty = rememberVectorPainter(
            image = Icons.Rounded.Star, color = MaterialTheme.colorScheme.surfaceVariant
        ),
        painterFilled = rememberVectorPainter(
            image = Icons.Rounded.Star, color = Gold
        ),
        modifier = modifier,
        size = size,
        spaceBetween = spaceBetween
    )*/
}