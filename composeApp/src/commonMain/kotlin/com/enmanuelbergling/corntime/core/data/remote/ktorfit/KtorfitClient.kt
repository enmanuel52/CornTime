package com.enmanuelbergling.corntime.core.data.remote.ktorfit

import com.enmanuelbergling.corntime.core.data.remote.BASE_URL
import com.enmanuelbergling.corntime.core.util.getSystemLanguage
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.plugin
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

const val API_KEY = ""

private val httpClient = HttpClient {

    install(HttpTimeout) {
        requestTimeoutMillis = 10000L
    }

    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            }
        )
    }

}.apply {
    //The HttpSend plugin doesn't require installation
    plugin(HttpSend).intercept { request ->
        val newRequest = request.apply {
            url {
                parameters.append(name = "api_key", value = API_KEY)
                parameters.append(name = "language", value = getSystemLanguage())
            }
        }
        execute(newRequest)
    }
}

val KtorfitClient = Ktorfit.Builder()
    .baseUrl(BASE_URL)
    .httpClient(
        httpClient
    )
    .build()
