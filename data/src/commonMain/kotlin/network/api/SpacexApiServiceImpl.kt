package network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import spacexdemo.domain.dto.CompanyInfo
import spacexdemo.domain.dto.Launch
import spacexdemo.domain.dto.SpacexApiService

private const val ALL = "https://api.spacexdata.com/v5/launches"
private const val COMPANY = "https://api.spacexdata.com/v4/company"

class SpacexApiServiceImpl(
    private val httpClient: HttpClient
) : SpacexApiService {

    override suspend fun all(): List<Launch> {
        val response = httpClient.get(ALL)
        return response.body()
    }

    override suspend fun company(): CompanyInfo {
        val response = httpClient.get(COMPANY)
        return response.body()
    }
}