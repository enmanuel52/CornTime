package com.enmanuelbergling.corntime.feature.movies.filter.model

import com.enmanuelbergling.corntime.core.model.movie.Genre
import com.enmanuelbergling.corntime.core.model.movie.SortCriteria

sealed interface MovieFilterEvent {
    /**
     * to select or not genre
     * */
    data class PickGenre(val genre: Genre) : MovieFilterEvent
    data class PickOrderCriteria(val sortCriteria: SortCriteria) : MovieFilterEvent
    data object Clear : MovieFilterEvent
}