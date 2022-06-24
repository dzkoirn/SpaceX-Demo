package spacexdemo.domain.dto

interface SpacexApiService {
    suspend fun all(): String
}