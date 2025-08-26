package com.enmanuelbergling.corntime.core.domain.usecase.user

import com.enmanuelbergling.corntime.core.domain.datasource.preferences.UserPreferenceDS

class GetSavedUserUC(private val preferenceDS: UserPreferenceDS) {
    operator fun invoke() = preferenceDS.getCurrentUser()
}