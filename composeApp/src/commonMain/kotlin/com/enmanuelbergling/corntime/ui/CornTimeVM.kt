package com.enmanuelbergling.corntime.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enmanuelbergling.core.domain.usecase.user.LogoutUC
import com.enmanuelbergling.corntime.core.domain.usecase.onboarding.FinishOnboardingUC
import com.enmanuelbergling.corntime.core.domain.usecase.onboarding.IsOnboardingUC
import com.enmanuelbergling.corntime.core.domain.usecase.settings.GetDarkThemeUC
import com.enmanuelbergling.corntime.core.domain.usecase.settings.GetDynamicColorUC
import com.enmanuelbergling.corntime.core.domain.usecase.user.GetSavedUserUC
import com.enmanuelbergling.corntime.core.util.System
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CornTimeVM(
    getDarkThemeUC: GetDarkThemeUC,
    getDynamicColorUC: GetDynamicColorUC,
    getSavedUserUC: GetSavedUserUC,
    isOnboardingUC: IsOnboardingUC,
    private val finishOnboardingUC: FinishOnboardingUC,
    private val logoutUC: LogoutUC,
    system: System,
) : ViewModel() {

    val isOnline = system.isNetworkAvailable()
    val darkTheme = getDarkThemeUC()

    val dynamicColor = getDynamicColorUC()

    val userDetails = getSavedUserUC()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            null,
        )

    val isOnboarding = isOnboardingUC()

    fun finishOnboarding() = viewModelScope.launch {
        finishOnboardingUC()
    }

    fun logout() = viewModelScope.launch {
        logoutUC()
    }
}