package com.enmanuelbergling.feature.movies.recommended

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import app.cash.paging.compose.collectAsLazyPagingItems
import com.enmanuelbergling.core.model.movie.Movie
import com.enmanuelbergling.core.ui.BASE_BACKDROP_IMAGE_URL
import com.enmanuelbergling.core.ui.components.HandlerPagingUiState
import com.enmanuelbergling.core.ui.components.common.RecommendedMovieCard
import com.enmanuelbergling.core.ui.components.common.RecommendedMovieCardPlaceholder
import com.enmanuelbergling.core.ui.components.listItemWindAnimation
import com.enmanuelbergling.core.ui.core.dimen
import com.enmanuelbergling.core.ui.core.isRefreshing
import com.enmanuelbergling.core.ui.core.isScrollingForward
import com.enmanuelbergling.feature.movies.details.DetailsImage
import com.enmanuelbergling.feature.movies.details.DetailsInformation
import corntime.feature.movies.generated.resources.Res
import corntime.feature.movies.generated.resources.back_icon
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendedMoviesScreen(movieId: Int, onMovie: (movieId: Int) -> Unit, onBack: () -> Unit) {
    val viewModel = koinViewModel<RecommendedMoviesVM> { parametersOf(movieId) }
    val movies = viewModel.movies.collectAsLazyPagingItems()

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    HandlerPagingUiState(items = movies, snackState = snackBarHostState)

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Recommended Movies") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = stringResource(
                            Res.string.back_icon
                        )
                    )
                }
            },
        )
    }, snackbarHost = { SnackbarHost(snackBarHostState) }) {
        val listState = rememberLazyListState()

        Box(
            Modifier.padding(it).fillMaxSize()
        ) {
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.small),
                contentPadding = PaddingValues(MaterialTheme.dimen.small)
            ) {
                items(movies.itemCount) { index ->
                    val movie = movies[index]
                    movie?.let {
                        if (index == 0) {
                            FirstRecommendation(
                                movie = movie,
                                modifier = Modifier.fillMaxWidth()
                                    .padding(
                                        horizontal = MaterialTheme.dimen.small,
                                        vertical = MaterialTheme.dimen.medium
                                    )
                                    .listItemWindAnimation(
                                        isScrollingForward = listState.isScrollingForward(),
                                        rotation = 4f,
                                        durationMillis = 200,
                                    )
                            ) { onMovie(movie.id) }
                        } else {
                            RecommendedMovieCard(
                                imageUrl = movie.backdropPath,
                                title = movie.title,
                                rating = movie.voteAverage,
                                year = movie.releaseYear,
                                modifier = Modifier.fillMaxWidth().listItemWindAnimation(
                                    isScrollingForward = listState.isScrollingForward(),
                                    rotation = 4f,
                                    durationMillis = 200
                                )
                            ) {
                                onMovie(movie.id)
                            }
                        }

                    }
                }

                if (movies.isRefreshing) {
                    items(50) {
                        RecommendedMovieCardPlaceholder()
                    }
                }
            }
        }
    }
}

@Composable
fun FirstRecommendation(
    movie: Movie,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.clickable { onClick() } then modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.superSmall),
    ) {
        DetailsImage(BASE_BACKDROP_IMAGE_URL + movie.backdropPath)

        DetailsInformation(
            title = movie.title,
            year = movie.releaseYear,
            rating = movie.voteAverage.toFloat(),
        )
    }
}