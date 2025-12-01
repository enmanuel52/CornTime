package com.enmanuelbergling.feature.auth.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.enmanuelbergling.feature.auth.LoginRoute
import kotlinx.serialization.Serializable

@Serializable
data object LoginDestination: NavKey

fun NavBackStack<NavKey>.navigateToLoginScreen() {
    add(LoginDestination)
}

fun EntryProviderScope<Any>.loginScreen(onLoginSucceed: () -> Unit, onBack: ()->Unit) {
    entry<LoginDestination> {
        LoginRoute(onLoginSucceed,onBack)
    }
}