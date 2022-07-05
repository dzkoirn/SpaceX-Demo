package storage

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import spacexdemo.domain.api.MutableRepo
import spacexdemo.domain.dto.FilterSettings

class SettingsRepo: MutableRepo<FilterSettings> {

    private val mutex = Mutex()
    private var settings: FilterSettings? = null

    override suspend fun set(value: FilterSettings) {
        mutex.withLock {
            settings = value
        }
    }

    override suspend fun get(): FilterSettings = mutex.withLock {
        settings ?: FilterSettings.DEFAULT
    }
}