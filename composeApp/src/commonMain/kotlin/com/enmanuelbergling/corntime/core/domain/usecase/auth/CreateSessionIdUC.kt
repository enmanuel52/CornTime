package com.enmanuelbergling.corntime.core.domain.usecase.auth

import com.enmanuelbergling.corntime.core.domain.datasource.preferences.AuthPreferenceDS
import com.enmanuelbergling.corntime.core.domain.datasource.remote.AuthRemoteDS
import com.enmanuelbergling.corntime.core.model.core.ResultHandler


class CreateSessionIdUC(private val remoteDS: AuthRemoteDS, private val localDS: AuthPreferenceDS) {
    /**
     * get sessionId that is required for user related data requests
     * and save it in preferences
     * */
    suspend operator fun invoke(token: String) = remoteDS.createSessionId(token).also {
        if (it is ResultHandler.Success) {
            val sessionId = it.data.orEmpty()
            localDS.saveSessionId(sessionId)
        }
    }
}