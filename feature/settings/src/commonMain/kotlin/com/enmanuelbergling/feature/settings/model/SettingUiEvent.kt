package com.enmanuelbergling.feature.settings.model


internal sealed interface SettingUiEvent {
    data class DarkThemeEvent(val theme: DarkThemeUi) : SettingUiEvent
    data class DynamicColor(val active: Boolean) : SettingUiEvent
    data object Logout : SettingUiEvent
    data object Login : SettingUiEvent
    data object DarkThemeMenu : SettingUiEvent
    data object DynamicColorMenu : SettingUiEvent
}