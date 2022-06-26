package spacexdemo.domain.api

import spacexdemo.domain.dto.CompanyInfo
import spacexdemo.domain.dto.Launch

interface SpacexApiService {
    suspend fun all(): List<Launch>
    suspend fun company(): CompanyInfo
}