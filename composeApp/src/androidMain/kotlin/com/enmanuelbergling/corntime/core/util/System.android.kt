package com.enmanuelbergling.corntime.core.util

import android.content.res.Resources
import androidx.core.os.ConfigurationCompat

actual class System {
}

actual fun getSystemLanguage(): String {
    val config = Resources.getSystem().configuration
    return ConfigurationCompat.getLocales(config)[0].toString().replace('_', '-')
}
