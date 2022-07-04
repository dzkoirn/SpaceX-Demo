package spacexdemo.domain

import spacexdemo.domain.api.MutableRepo
import spacexdemo.domain.dto.FilterSettings

class FilterUseCase(private val settingsRepo: MutableRepo<FilterSettings>) {

    suspend fun getSettings() = settingsRepo.get()
    suspend fun saveSetting(filterSettings: FilterSettings) {
        settingsRepo.set(filterSettings)
    }
}