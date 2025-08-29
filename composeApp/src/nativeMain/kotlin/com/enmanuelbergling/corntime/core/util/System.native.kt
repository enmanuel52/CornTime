package com.enmanuelbergling.corntime.core.util

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

actual class System {
}

actual fun getSystemLanguage(): String {
    return NSLocale.currentLocale.languageCode
}
