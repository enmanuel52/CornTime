package com.enmanuelbergling.corntime

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.rememberNavBackStack
import com.enmanuelbergling.core.model.settings.DarkTheme
import com.enmanuelbergling.core.ui.theme.CornTimeTheme
import com.enmanuelbergling.corntime.navigation.NavConfiguration
import com.enmanuelbergling.corntime.ui.CornTimeVM
import com.enmanuelbergling.corntime.ui.CornsTimeApp
import com.enmanuelbergling.corntime.ui.OnboardingScreen
import com.enmanuelbergling.corntime.ui.rememberCornTimeAppState
import com.enmanuelbergling.feature.movies.navigation.MoviesDestination
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

    val navBackStack = rememberNavBackStack(
        configuration = NavConfiguration,
        MoviesDestination,
    )

    val appState = rememberCornTimeAppState(
        isOnline = isOnlineState,
        navBackStack = navBackStack,
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