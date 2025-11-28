package com.enmanuelbergling.corntime

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.enmanuelbergling.core.model.settings.DarkTheme
import com.enmanuelbergling.core.ui.theme.CornTimeTheme
import com.enmanuelbergling.corntime.ui.CornTimeVM
import com.enmanuelbergling.corntime.ui.CornsTimeApp
import com.enmanuelbergling.corntime.ui.OnboardingScreen
import com.enmanuelbergling.corntime.ui.rememberCornTimeAppState
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val viewModel = koinViewModel<CornTimeVM>()

    val isOnlineState by viewModel.isOnline.collectAsStateWithLifecycle(initialValue = true)
    val darkTheme by viewModel.darkTheme.collectAsStateWithLifecycle(initialValue = DarkTheme.System)
    val dynamicColor by viewModel.dynamicColor.collectAsStateWithLifecycle(initialValue = false)

    val userDetails by viewModel.userDetails.collectAsStateWithLifecycle()
    val isOnboarding by viewModel.isOnboarding.collectAsStateWithLifecycle(initialValue = false)

    val appState = rememberCornTimeAppState(
        isOnline = isOnlineState,
    )

    CornTimeTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        CornsTimeApp(
            state = appState,
            userDetails = userDetails,
            onLogout = viewModel::logout
        )

        if (isOnboarding) {
            OnboardingScreen(viewModel::finishOnboarding)
        }

    }
}