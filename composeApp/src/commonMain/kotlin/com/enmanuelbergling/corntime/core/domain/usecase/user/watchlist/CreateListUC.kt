package com.enmanuelbergling.corntime.core.domain.usecase.user.watchlist

import com.enmanuelbergling.corntime.core.domain.datasource.remote.UserRemoteDS
import com.enmanuelbergling.corntime.core.model.user.CreateListPost

class CreateListUC(private val remoteDS: UserRemoteDS) {

    suspend operator fun invoke(
        listPost: CreateListPost,
        sessionId: String,
    ) = remoteDS.createWatchList(listPost, sessionId)
}