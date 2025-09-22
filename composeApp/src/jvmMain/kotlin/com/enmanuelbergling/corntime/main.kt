package com.enmanuelbergling.corntime

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.enmanuelbergling.corntime.di.initKoin

fun main() {
    initKoin()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "CornTime",
        ) {
            App()
        }
    }
}