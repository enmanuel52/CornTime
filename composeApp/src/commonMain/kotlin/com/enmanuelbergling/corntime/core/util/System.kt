package com.enmanuelbergling.corntime.core.util

expect class System {

}

/**
 * Retrieves the system language code.
 *
 * This function is expected to be implemented in platform-specific code.
 *
 * @return A string representing the system language code, including the region if available (e.g., "en-US", "es-ES").
 */
expect fun getSystemLanguage(): String
