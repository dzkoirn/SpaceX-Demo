package network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import spacexdemo.domain.dto.SpacexApiService

private const val ALL = "https://api.spacexdata.com/v5/launches"

class SpacexApiServiceImpl(
    private val httpClient: HttpClient
) : SpacexApiService {

    override suspend fun all(): String {
        val response = httpClient.get(ALL)
        return response.body()
    }
}