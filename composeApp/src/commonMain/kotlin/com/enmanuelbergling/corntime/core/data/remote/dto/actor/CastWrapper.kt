package com.enmanuelbergling.corntime.core.data.remote.dto.actor

import kotlinx.serialization.Serializable

@Serializable
internal data class CastWrapperDTO(
    val cast: List<KnownMovieDTO>,
)
