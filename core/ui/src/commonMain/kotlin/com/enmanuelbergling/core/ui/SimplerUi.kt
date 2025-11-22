package com.enmanuelbergling.core.ui

import org.jetbrains.compose.resources.StringResource

sealed interface SimplerUi {
    data object Loading : SimplerUi
    data object Idle : SimplerUi
    data object Success : SimplerUi
    /**
     * @param messageRes must be a string resource
     * */
    data class Error(val messageRes: StringResource) : SimplerUi
}