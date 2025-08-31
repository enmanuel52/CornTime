package com.enmanuelbergling.corntime

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.enmanuelbergling.corntime.core.model.settings.DarkTheme
import com.enmanuelbergling.corntime.core.ui.theme.CornTimeTheme
import com.enmanuelbergling.corntime.ui.CornTimeVM
import com.enmanuelbergling.corntime.ui.CornsTimeApp
import com.enmanuelbergling.corntime.ui.OnboardingScreen
import com.enmanuelbergling.corntime.ui.rememberCornTimeAppState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import corntime.composeapp.generated.resources.Res
import corntime.composeapp.generated.resources.compose_multiplatform
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

    CornTimeTheme(darkTheme = darkTheme, dynamicColor = dynamicColor) {
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