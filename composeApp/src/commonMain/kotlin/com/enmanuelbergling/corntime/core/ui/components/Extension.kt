package com.enmanuelbergling.corntime.core.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ModeNight
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.vector.ImageVector
import com.enmanuelbergling.corntime.core.model.core.NetworkException
import com.enmanuelbergling.corntime.core.model.settings.DarkTheme
import corntime.composeapp.generated.resources.Res
import corntime.composeapp.generated.resources.default_net_exception_message
import corntime.composeapp.generated.resources.net_time_out_exception_message
import corntime.composeapp.generated.resources.user_unauthorized_message
import org.jetbrains.compose.resources.StringResource

val DarkTheme.icon: ImageVector
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        DarkTheme.No -> Icons.Rounded.WbSunny
        DarkTheme.Yes -> Icons.Rounded.ModeNight
        DarkTheme.System -> if (isSystemInDarkTheme()) Icons.Rounded.ModeNight else Icons.Rounded.WbSunny
    }

val NetworkException.messageResource: StringResource
    get() = when(this){
        NetworkException.AuthorizationException -> Res.string.user_unauthorized_message
        NetworkException.DefaultException -> Res.string.default_net_exception_message
        NetworkException.ReadTimeOutException -> Res.string.net_time_out_exception_message
    }