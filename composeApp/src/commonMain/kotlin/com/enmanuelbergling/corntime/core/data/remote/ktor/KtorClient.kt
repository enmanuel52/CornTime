package com.enmanuelbergling.corntime.core.data.remote.ktor

import CornTime.composeApp.BuildConfig
import com.enmanuelbergling.corntime.core.data.remote.BASE_URL
import com.enmanuelbergling.core.model.core.NetworkException
import com.enmanuelbergling.corntime.core.util.getSystemLanguage
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

typealias KtorClient = HttpClient

@OptIn(ExperimentalSerializationApi::class)
fun getKtorClient(engine: HttpClientEngine): HttpClient = HttpClient(engine) {
    defaultRequest {
        url(BASE_URL)
        url {
            parameters.append(name = "api_key", value = BuildConfig.API_KEY)
            parameters.append(name = "language", value = getSystemLanguage())
        }
    }

    install(HttpTimeout) {
        requestTimeoutMillis = 30000L
    }

    HttpResponseValidator {
        validateResponse { httpResponse ->
            val statusCode = httpResponse.status.value

            /*if (httpResponse.responseTime.seconds >= 30) {
                Log.d(TAG, "networkCall: read time out")

                throw NetworkException.ReadTimeOutException
            }*/

            when (statusCode) {
                in 200 until 300 -> {}
                401 -> throw NetworkException.AuthorizationException
                else -> throw NetworkException.DefaultException
            }
        }
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

    install(Logging) {
        level = LogLevel.ALL
        sanitizeHeader { header -> header == HttpHeaders.Authorization }
    }
}