package network.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlin.time.DurationUnit
import kotlin.time.toDuration

expect fun provideHttpClientEngine(): HttpClientEngine

object DefaultHttpClientConfig {
    val timeout = 60.toDuration(DurationUnit.SECONDS)
}

fun provideHttpClient(
    engine: HttpClientEngine = provideHttpClientEngine()
): HttpClient =
    // By default ktor can found engine from dependencies automaticaly. But it is look too magic for me. Let's declare it by hand
    HttpClient(engine) {
        install(ContentNegotiation) {
            json()
        }
    }