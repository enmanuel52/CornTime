package com.enmanuelbergling.corntime.data

import com.enmanuelbergling.corntime.core.model.actor.Actor
import com.enmanuelbergling.corntime.core.model.actor.ActorDetails

object FakeActorData {

    val ACTOR_DETAILS = ActorDetails(
        adult = false,
        alsoKnownAs = listOf(),
        biography = "",
        birthday = "",
        deathday = null,
        gender = 1,
        homepage = null,
        id = 0,
        imdbId = "",
        knownForDepartment = "",
        name = "Tim Bergling",
        placeOfBirth = "Sweden",
        popularity = 10.0,
        profilePath = "tim_path"
    )

    val ACTOR = Actor(
        adult = false,
        gender = 1,
        id = 0,
        knownForDepartment = "",
        name = "Tim Bergling",
        popularity = 10.0,
        profilePath = "tim_path",
        originalName = "Tim"
    )
}