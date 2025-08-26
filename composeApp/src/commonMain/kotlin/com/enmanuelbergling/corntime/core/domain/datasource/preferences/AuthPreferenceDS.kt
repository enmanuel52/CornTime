package com.enmanuelbergling.corntime.core.domain.datasource.preferences

import kotlinx.coroutines.flow.Flow

interface AuthPreferenceDS {
    fun saveSessionId(sessionId: String)

    fun getSessionId(): Flow<String>

    fun clear()
}