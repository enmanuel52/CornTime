package com.enmanuelbergling.corntime.core.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.enmanuelbergling.corntime.core.ui.components.RatingStars
import com.enmanuelbergling.corntime.core.model.movie.Movie
import com.enmanuelbergling.corntime.core.ui.core.dimen
import com.enmanuelbergling.corntime.core.ui.theme.CornTimeTheme
import com.enmanuelbergling.corntime.core.util.BASE_POSTER_IMAGE_URL
import corntime.composeapp.generated.resources.Res
import corntime.composeapp.generated.resources.pop_corn_and_cinema_poster
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieLandCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    ElevatedCard(
        onClick = onClick, modifier = modifier
            .heightIn(max = 120.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            AsyncImage(
                model = BASE_POSTER_IMAGE_URL + movie.posterPath,
                contentDescription = "movie image",
                error = painterResource(Res.drawable.pop_corn_and_cinema_poster),
                placeholder = painterResource(Res.drawable.pop_corn_and_cinema_poster),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(.65f)
                    .clip(MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.width(MaterialTheme.dimen.mediumSmall))

            MovieLandInformation(
                title = movie.title,
                year = movie.releaseYear,
                voteAverage = movie.voteAverage,
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}

@Preview
@Composable
fun MovieLandCardPlaceholder(
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier
            .heightIn(max = 120.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(.6f)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        MaterialTheme.shapes.medium
                    )
            )

            Spacer(modifier = Modifier.width(MaterialTheme.dimen.mediumSmall))

            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly) {
                Box(
                    modifier = Modifier
                        .height(MaterialTheme.dimen.large)
                        .fillMaxWidth(.75f)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            shape = MaterialTheme.shapes.small
                        )
                )

                RatingStars(value = 0f, spaceBetween = 3.dp, size = 24.dp)
            }
        }

    }
}

@Composable
fun MovieLandInformation(
    title: String,
    year: String,
    voteAverage: Double,
    modifier: Modifier = Modifier,
) {
    Column(modifier, verticalArrangement = Arrangement.SpaceEvenly) {
        Text(
            //getting year
            text = "$title ($year)",
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold
        )

        //it comes 10 for server
        RatingStars(value = voteAverage.div(2).toFloat(), spaceBetween = 3.dp, size = 24.dp)
    }
}

@Preview
@Composable
fun MovieLandInformationPrev() {
    CornTimeTheme {
        MovieLandInformation(
            title = "The Beekeeper",
            year = "2024",
            voteAverage = 8.9,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimen.small)
                .height(120.dp)
        )
    }
}