package com.enmanuelbergling.corntime.core.model.core

data class FormValidation(
    val isSuccess: Boolean = true,
    val errorMessage: String? = null,
)
