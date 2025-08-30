package com.enmanuelbergling.corntime.feature.movies.details.model

import com.enmanuelbergling.corntime.core.model.movie.Cast
import com.enmanuelbergling.corntime.core.model.movie.Crew

data class PersonUiItem(
    val id: Int,
    val imageUrl: String?,
    val name: String,
)

fun Cast.toPersonUi() = PersonUiItem(id, profilePath, name)
fun Crew.toPersonUi() = PersonUiItem(id, profilePath, name)
