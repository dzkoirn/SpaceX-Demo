package storage

import spacexdemo.domain.api.Repo

class CachedRepo<T>(
    private val dataSource: suspend () -> T
) : Repo<T> {

    private var cachedData: T? = null

    override suspend fun get(): T = cachedData ?: dataSource().also { cachedData = it }
}