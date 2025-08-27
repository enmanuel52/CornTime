package com.enmanuelbergling.corntime.datasource.remote

import com.enmanuelbergling.corntime.core.domain.datasource.remote.AuthRemoteDS
import com.enmanuelbergling.corntime.core.model.RequestToken
import com.enmanuelbergling.corntime.core.model.SessionId
import com.enmanuelbergling.corntime.core.model.auth.CreateSessionPost
import com.enmanuelbergling.corntime.core.model.core.ResultHandler

class FakeAuthRemoteDS : AuthRemoteDS {

    override suspend fun createRequestToken(): ResultHandler<RequestToken> =
        ResultHandler.Success("new token")

    override suspend fun createSessionFromLogin(sessionPost: CreateSessionPost): ResultHandler<RequestToken> =
        ResultHandler.Success("latest token")

    override suspend fun createSessionId(token: RequestToken): ResultHandler<SessionId> =
        ResultHandler.Success("new session id")
}