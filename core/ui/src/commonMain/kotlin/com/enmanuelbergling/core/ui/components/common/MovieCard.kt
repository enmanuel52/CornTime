package com.enmanuelbergling.core.ui.components.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.enmanuelbergling.core.ui.BASE_BACKDROP_IMAGE_URL
import com.enmanuelbergling.core.ui.BASE_POSTER_IMAGE_URL
import com.enmanuelbergling.core.ui.components.RatingStars
import com.enmanuelbergling.core.ui.core.BoundsTransition
import com.enmanuelbergling.core.ui.core.LocalSharedTransitionScope
import com.enmanuelbergling.core.ui.core.dimen
import com.enmanuelbergling.core.ui.theme.CornTimeTheme
import corntime.core.ui.generated.resources.Res
import corntime.core.ui.generated.resources.pop_corn_and_cinema_backdrop
import corntime.core.ui.generated.resources.pop_corn_and_cinema_poster
import org.jetbrains.compose.resources.painterResource

/**
 * @param rating between 1 and 5 showed as yellow stars
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(
    imageUrl: String,
    title: String,
    rating: Double,
    modifier: Modifier = Modifier,
    titleLines: Int = 1,
    onClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.small),
        modifier = modifier
    ) {
        ElevatedCard(
            onClick = onClick,
            modifier = Modifier
                .animateContentSize(
                    spring(Spring.DampingRatioLowBouncy, Spring.StiffnessMediumLow)
                ),
        ) {
            AsyncImage(
                model = BASE_POSTER_IMAGE_URL + imageUrl,
                contentDescription = "movie image",
                error = painterResource(Res.drawable.pop_corn_and_cinema_poster),
                placeholder = painterResource(Res.drawable.pop_corn_and_cinema_poster),
                modifier = Modifier.aspectRatio(.65f),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = titleLines,
            overflow = TextOverflow.Ellipsis
        )
        RatingStars(value = rating.div(2).toFloat(), size = 16.dp, spaceBetween = 1.dp)
    }
}

@Preview
@Composable
fun MovieCardPrev() {
    CornTimeTheme {
        MovieCard(imageUrl = "", title = "Joker", rating = 3.5) {}
    }
}

@Preview
@Composable
fun MovieCardPlaceholder(modifier: Modifier = Modifier) {
    Column(
        modifier.widthIn(max = 200.dp),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.small)
    ) {
        Box(
            Modifier
                .aspectRatio(.65f)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    CardDefaults.elevatedShape
                )
        )
        Box(
            modifier = modifier
                .fillMaxWidth(.5f)
                .height(MaterialTheme.dimen.lessLarge)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    MaterialTheme.shapes.small
                )
        )
        RatingStars(value = 0f)
    }
}

@Composable
fun HeaderMovieCard(
    imageUrl: String,
    title: String,
    rating: Double,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(modifier) {
        ElevatedCard(onClick = onClick) {
            AsyncImage(
                model = BASE_BACKDROP_IMAGE_URL + imageUrl,
                contentDescription = "header image",
                placeholder = painterResource(
                    Res.drawable.pop_corn_and_cinema_backdrop
                ),
                error = painterResource(
                    Res.drawable.pop_corn_and_cinema_backdrop
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.78f),
                contentScale = ContentScale.FillWidth
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.dimen.small))

        HeaderMovieInfo(
            title = title,
            rating = rating,
            modifier = Modifier.padding(MaterialTheme.dimen.small)
        )
    }
}

@Composable
fun HeaderMovieInfo(
    title: String,
    rating: Double,
    modifier: Modifier = Modifier,
) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.width(MaterialTheme.dimen.verySmall))
        RatingStars(value = rating.div(2).toFloat(), spaceBetween = 1.dp)
    }
}

@Preview
@Composable
fun HeaderMovieCardPrev() {
    HeaderMovieCard(
        imageUrl = "", title = "Joker", rating = 3.5
    ) {}
}

@Preview
@Composable
fun HeaderMoviePlaceholder(modifier: Modifier = Modifier) {
    Column(modifier) {
        Box(
            modifier = Modifier
                .aspectRatio(1.6f)
                .background(MaterialTheme.colorScheme.surfaceVariant, CardDefaults.elevatedShape)
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimen.small))

        HeaderMovieInfoPlaceholder()
    }
}

@Composable
private fun HeaderMovieInfoPlaceholder() {
    Row {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(16.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    MaterialTheme.shapes.small
                )
        )
        Spacer(modifier = Modifier.width(MaterialTheme.dimen.verySmall))
        RatingStars(value = 0f)
    }
}


/**
 * @param rating between 1 and 5 showed as yellow stars
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedContentScope.RecommendedMovieCard(
    imageUrl: String?,
    title: String,
    year: String,
    rating: Double,
    modifier: Modifier = Modifier,
    titleLines: Int = 2,
    onClick: () -> Unit,
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current!!

    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(MaterialTheme.dimen.small) then modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ElevatedCard(
            modifier = Modifier
                .animateContentSize(
                    spring(Spring.DampingRatioLowBouncy, Spring.StiffnessMediumLow)
                )
                .weight(.4f),
        ) {
            AsyncImage(
                model = BASE_BACKDROP_IMAGE_URL + imageUrl,
                contentDescription = "movie image",
                error = painterResource(Res.drawable.pop_corn_and_cinema_poster),
                placeholder = painterResource(Res.drawable.pop_corn_and_cinema_poster),
                modifier = Modifier.aspectRatio(1.35f)
                        then with(sharedTransitionScope) {
                    Modifier.sharedElement(
                        sharedContentState = rememberSharedContentState(key = imageUrl.orEmpty()),
                        animatedVisibilityScope = this@RecommendedMovieCard,
                        boundsTransform = BoundsTransition
                    )
                        .clip(CardDefaults.elevatedShape)
                },
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.width(MaterialTheme.dimen.medium))

        Column(
            modifier = Modifier.weight(.6f),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.small)
        ) {
            Text(
                text = title,
                maxLines = titleLines,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Normal
            )
            Text("($year)", style = MaterialTheme.typography.labelLarge)
            RatingStars(
                value = rating.div(2).toFloat(), size = 16.dp, spaceBetween = 1.dp,
            )
        }
    }
}

@Preview
@Composable
fun RecommendedMovieCardPrev() {
    CornTimeTheme {
        val shown = true
        AnimatedContent(
            shown
        ) {
            RecommendedMovieCard(
                imageUrl = "",
                title = LoremIpsum(10).values.joinToString(),
                rating = 3.5,
                year = "2025"
            ) {}
        }
    }
}

@Preview
@Composable
fun RecommendedMovieCardPlaceholder(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(MaterialTheme.dimen.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier
                .aspectRatio(1.35f)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    CardDefaults.elevatedShape
                )
                .weight(.4f)
        )
        Spacer(Modifier.width(MaterialTheme.dimen.medium))
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.small),
            modifier = Modifier.weight(.6f)
        ) {
            repeat(2) {
                Box(
                    modifier = modifier
                        .fillMaxWidth(.9f)
                        .height(MaterialTheme.dimen.medium)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            MaterialTheme.shapes.small
                        )
                )
            }
            RatingStars(
                value = 0f,
                size = 16.dp, spaceBetween = 1.dp,
            )
        }
    }
}