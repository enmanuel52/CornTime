package com.enmanuelbergling.corntime.core.domain.datasource.preferences

import kotlinx.coroutines.flow.Flow

interface OnboardingPreferenceDS {
    fun isOnboarding(): Flow<Boolean>

    suspend fun finishOnboarding()
}