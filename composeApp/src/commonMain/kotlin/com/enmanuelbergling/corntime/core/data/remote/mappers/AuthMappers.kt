package com.enmanuelbergling.corntime.core.data.remote.mappers

import com.enmanuelbergling.corntime.core.data.remote.dto.auth.CreateSessionBody
import com.enmanuelbergling.core.model.auth.CreateSessionPost

internal fun CreateSessionPost.asBody() = CreateSessionBody(
    username = username,
    password = password,
    requestToken = requestToken
)