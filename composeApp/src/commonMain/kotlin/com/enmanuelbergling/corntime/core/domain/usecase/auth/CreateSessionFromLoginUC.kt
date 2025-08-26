package com.enmanuelbergling.corntime.core.domain.usecase.auth

import com.enmanuelbergling.corntime.core.domain.datasource.remote.AuthRemoteDS
import com.enmanuelbergling.corntime.core.model.auth.CreateSessionPost

class CreateSessionFromLoginUC(private val remoteDS: AuthRemoteDS) {

    /**
     *@param sessionPost validate a request token by entering it
     * the request token won´t change*/
    suspend operator fun invoke(sessionPost: CreateSessionPost) =
        remoteDS.createSessionFromLogin(sessionPost)
}