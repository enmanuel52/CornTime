package com.enmanuelbergling.core.remote.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RequestTokenBody(
    @SerialName("request_token") val token: String,
)
