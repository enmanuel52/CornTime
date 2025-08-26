package com.enmanuelbergling.corntime.core.domain.datasource.remote

import com.enmanuelbergling.corntime.core.model.RequestToken
import com.enmanuelbergling.corntime.core.model.SessionId
import com.enmanuelbergling.corntime.core.model.auth.CreateSessionPost
import com.enmanuelbergling.corntime.core.model.core.ResultHandler

interface AuthRemoteDS : RemoteDataSource {
    suspend fun createRequestToken(): ResultHandler<RequestToken>

    suspend fun createSessionFromLogin(sessionPost: CreateSessionPost): ResultHandler<RequestToken>

    suspend fun createSessionId(token: RequestToken): ResultHandler<SessionId>
}