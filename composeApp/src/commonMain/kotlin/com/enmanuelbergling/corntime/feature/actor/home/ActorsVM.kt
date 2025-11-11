package com.enmanuelbergling.corntime.feature.actor.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.enmanuelbergling.corntime.feature.actor.paging.GetPopularActorsUC

internal class ActorsVM(
    getActors: GetPopularActorsUC,
) : ViewModel() {

    val actors = getActors().cachedIn(viewModelScope)
}