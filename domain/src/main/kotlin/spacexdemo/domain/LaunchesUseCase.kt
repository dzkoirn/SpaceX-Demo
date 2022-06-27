package spacexdemo.domain

import spacexdemo.domain.api.Repo
import spacexdemo.domain.dto.Launch

class LaunchesUseCase(
    private val repo: Repo<List<Launch>>
) {
    suspend fun getLaunches(): List<Launch> = repo.get()
}