package com.enmanuelbergling.corntime.core.util

import java.util.Locale

actual class System {
}

actual fun getSystemLanguage(): String {
    return Locale.getDefault().toLanguageTag()
}
