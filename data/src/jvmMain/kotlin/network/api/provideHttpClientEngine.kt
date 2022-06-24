package network.api

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import kotlin.time.toJavaDuration

actual fun provideHttpClientEngine(): HttpClientEngine = OkHttp.create {
    config {
        connectTimeout(DefaultHttpClientConfig.timeout.toJavaDuration())
    }
}