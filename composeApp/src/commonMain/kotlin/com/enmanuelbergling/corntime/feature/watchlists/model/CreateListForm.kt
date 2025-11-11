package com.enmanuelbergling.corntime.feature.watchlists.model

import com.enmanuelbergling.corntime.core.model.user.CreateListPost

data class CreateListForm(
    val name: String = "",
    val nameError: String? = null,
    val description: String = "",
    val descriptionError: String? = null,
    val isVisible: Boolean = false,
) {
    fun toPost() = CreateListPost(name, description)
}
