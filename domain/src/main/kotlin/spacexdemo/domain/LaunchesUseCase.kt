package spacexdemo.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import spacexdemo.domain.api.Repo
import spacexdemo.domain.dto.*

class LaunchesUseCase(
    private val launchesRepo: Repo<List<Launch>>,
    private val rocketsRepo: Repo<List<Rocket>>,
    private val filterSettings: Repo<FilterSettings>,
) {
    suspend fun getLaunches(): List<LaunchInfo> = coroutineScope {
        val settings = filterSettings.get()
        val rockets = async(Dispatchers.IO) { rocketsRepo.get().associateBy { r -> r.id } }
        val launches = async(Dispatchers.IO) { launchesRepo.get() }

        val filterRange = LongRange(
            yearToEpochSeconds(settings.yearsRange.first),
            yearToEpochSeconds(settings.yearsRange.last),
        )

        rockets.await().let { rocketsMap ->
            launches.await()
                .filter { l -> l.date_unix in filterRange }
                .filter { l ->
                    when (settings.launchSuccess) {
                        LaunchSuccess.ALL -> true
                        LaunchSuccess.SUCCESSFULL -> l.success == true
                        LaunchSuccess.FAILED -> l.success == false
                    }
                }
                .map { l -> LaunchInfo(l, rocketsMap[l.rocket]!!) }
                .let {
                    if (settings.sortOrder == SortOrder.DESC) {
                        it.asReversed()
                    } else {
                        it
                    }
                }
        }
    }
}