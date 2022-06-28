package spacexdemo.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import spacexdemo.domain.api.Repo
import spacexdemo.domain.dto.Launch
import spacexdemo.domain.dto.LaunchInfo
import spacexdemo.domain.dto.Rocket

class LaunchesUseCase(
    private val launchesRepo: Repo<List<Launch>>,
    private val rocketsRepo: Repo<List<Rocket>>
) {
    suspend fun getLaunches(): List<LaunchInfo> = coroutineScope {
        val rockets = async(Dispatchers.IO) { rocketsRepo.get().associateBy { r -> r.id } }
        val launches = async(Dispatchers.IO) { launchesRepo.get() }

        rockets.await().let { rocketsMap ->
            launches.await().map { l -> LaunchInfo(l, rocketsMap[l.rocket]!!) }
        }
    }
}