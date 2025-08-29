package com.enmanuelbergling.corntime.core.data.remote.ktor.datasource

import com.enmanuelbergling.corntime.core.data.remote.ktor.service.AuthService
import com.enmanuelbergling.corntime.core.data.remote.dto.auth.RequestTokenBody
import com.enmanuelbergling.corntime.core.data.remote.mappers.asBody
import com.enmanuelbergling.corntime.core.domain.datasource.remote.AuthRemoteDS
import com.enmanuelbergling.corntime.core.model.RequestToken
import com.enmanuelbergling.corntime.core.model.SessionId
import com.enmanuelbergling.corntime.core.model.auth.CreateSessionPost
import com.enmanuelbergling.corntime.core.model.core.ResultHandler

internal class AuthRemoteDSImpl(private val service: AuthService) : AuthRemoteDS {
    override suspend fun createRequestToken(): ResultHandler<RequestToken> = safeKtorCall {

        val result = service.createRequestToken()

        result.token
    }

    override suspend fun createSessionFromLogin(sessionPost: CreateSessionPost): ResultHandler<RequestToken> =
        safeKtorCall {

            val result = service.createSessionFromLogin(sessionPost.asBody())

            result.token
        }

    override suspend fun createSessionId(token: RequestToken): ResultHandler<SessionId> =
        safeKtorCall {

            val result = service.createSessionId(RequestTokenBody(token))

            result.sessionId
        }
}