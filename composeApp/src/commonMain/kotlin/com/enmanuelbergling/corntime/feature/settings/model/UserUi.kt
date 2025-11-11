package com.enmanuelbergling.corntime.feature.settings.model

import com.enmanuelbergling.corntime.core.model.user.UserDetails

internal data class UserUi(
    val username: String = "",
    val avatarPath: String = "",
)

internal fun UserDetails.toSettingsUi() = UserUi(username, avatarPath)