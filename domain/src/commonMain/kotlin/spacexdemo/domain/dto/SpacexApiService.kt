package spacexdemo.domain.dto

interface SpacexApiService {
    suspend fun all(): List<Launch>
}