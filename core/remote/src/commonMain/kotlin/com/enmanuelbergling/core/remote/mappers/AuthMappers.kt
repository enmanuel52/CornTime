package com.enmanuelbergling.core.remote.mappers

import com.enmanuelbergling.core.remote.dto.auth.CreateSessionBody
import com.enmanuelbergling.core.model.auth.CreateSessionPost

internal fun CreateSessionPost.asBody() = CreateSessionBody(
    username = username,
    password = password,
    requestToken = requestToken
)