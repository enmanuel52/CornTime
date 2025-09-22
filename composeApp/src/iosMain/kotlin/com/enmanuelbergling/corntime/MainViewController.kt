package com.enmanuelbergling.corntime

import androidx.compose.ui.window.ComposeUIViewController
import com.enmanuelbergling.corntime.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }