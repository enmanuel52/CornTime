package com.enmanuelbergling.core.domain.usecase.form

import com.enmanuelbergling.corntime.core.model.core.FormValidation

class BasicFormValidationUC {
    operator fun invoke(text: String) =
        if (text.isBlank()) FormValidation(false, "This field is required")
        else FormValidation()
}
