package spacexdemo.domain.dto

interface SpacexApiService {
    suspend fun all(): List<Launch>
    suspend fun company(): CompanyInfo
}