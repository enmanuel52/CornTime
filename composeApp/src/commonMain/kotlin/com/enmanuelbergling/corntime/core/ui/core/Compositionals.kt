package com.enmanuelbergling.corntime.core.ui.core

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.enmanuelbergling.corntime.core.ui.theme.Dimen

val LocalDimen: ProvidableCompositionLocal<Dimen> = compositionLocalOf { Dimen() }