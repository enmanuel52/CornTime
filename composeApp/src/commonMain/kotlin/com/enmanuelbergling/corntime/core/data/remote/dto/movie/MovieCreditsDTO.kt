package com.enmanuelbergling.corntime.core.data.remote.dto.movie


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieCreditsDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("cast")
    val cast: List<CastDTO>,
    @SerialName("crew")
    val crew: List<CrewDTO>
)