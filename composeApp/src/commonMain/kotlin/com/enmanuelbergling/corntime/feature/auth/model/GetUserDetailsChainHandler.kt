package com.enmanuelbergling.corntime.feature.auth.model

import com.enmanuelbergling.corntime.core.domain.design.CannotHandleException
import com.enmanuelbergling.corntime.core.domain.design.ChainHandler
import com.enmanuelbergling.corntime.core.domain.usecase.user.GetUserDetailsUC
import com.enmanuelbergling.corntime.core.model.core.ResultHandler

class GetUserDetailsChainHandler(
    private val getUserDetailsUC: GetUserDetailsUC
): ChainHandler<LoginChainState> {

    override val nextChainHandler: ChainHandler<LoginChainState>?
        get() = null

    override suspend fun handle(request: LoginChainState) =
        when (val result = getUserDetailsUC()) {
            is ResultHandler.Error -> throw CannotHandleException(result.exception.message.orEmpty())
            is ResultHandler.Success -> Unit
        }
}