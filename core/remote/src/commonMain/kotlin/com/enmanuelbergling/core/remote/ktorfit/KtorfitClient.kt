package com.enmanuelbergling.core.remote.ktorfit

import CornTime.core.remote.BuildConfig
import com.enmanuelbergling.core.remote.BASE_URL
import com.enmanuelbergling.core.remote.getSystemLanguage
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.plugin
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

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
                parameters.append(name = "api_key", value = BuildConfig.API_KEY)
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
