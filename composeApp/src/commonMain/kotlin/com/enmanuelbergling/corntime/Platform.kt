package com.enmanuelbergling.corntime

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform