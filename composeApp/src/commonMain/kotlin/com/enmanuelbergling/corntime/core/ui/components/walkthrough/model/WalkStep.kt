package com.enmanuelbergling.corntime.core.ui.components.walkthrough.model

import org.jetbrains.compose.resources.DrawableResource

/**
 * @param title when null just hide and make description bigger
 * */
data class WalkStep(
     val imageResource: DrawableResource,
    val title: String? = null,
    val description: String,
)
