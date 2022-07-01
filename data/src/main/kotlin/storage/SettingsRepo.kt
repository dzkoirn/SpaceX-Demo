package storage

import spacexdemo.domain.api.MutableRepo
import spacexdemo.domain.dto.FilterSettings

class SettingsRepo : MutableRepo<FilterSettings> {

    private var settings: FilterSettings? = null

    override suspend fun set(value: FilterSettings) {
        settings = value
    }

    override suspend fun get(): FilterSettings = settings ?: FilterSettings.DEFAULT
}