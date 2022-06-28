package network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import spacexdemo.domain.dto.CompanyInfo
import spacexdemo.domain.dto.Launch
import spacexdemo.domain.dto.Rocket

private const val ALL = "https://api.spacexdata.com/v5/launches"
private const val COMPANY = "https://api.spacexdata.com/v4/company"
private const val ROCKETS = "https://api.spacexdata.com/v4/rockets"

class SpacexApiService(
    private val httpClient: HttpClient
) {

    suspend fun all(): List<Launch> {
        val response = httpClient.get(ALL)
        return response.body()
    }

    suspend fun company(): CompanyInfo {
        val response = httpClient.get(COMPANY)
        return response.body()
    }

    suspend fun rockets(): List<Rocket> {
        val response = httpClient.get(ROCKETS)
        return response.body()
    }
}