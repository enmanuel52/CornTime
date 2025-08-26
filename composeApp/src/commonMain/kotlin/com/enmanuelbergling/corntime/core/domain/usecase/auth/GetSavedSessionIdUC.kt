package com.enmanuelbergling.corntime.core.domain.usecase.auth

import com.enmanuelbergling.corntime.core.domain.datasource.preferences.AuthPreferenceDS

class GetSavedSessionIdUC(private val authPreferenceDS: AuthPreferenceDS) {

    operator fun invoke() = authPreferenceDS.getSessionId()
}