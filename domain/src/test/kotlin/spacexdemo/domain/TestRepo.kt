package spacexdemo.domain

import spacexdemo.domain.api.Repo

class TestRepo<T>(
    private val dataSource: () -> T
) : Repo<T> {
    var callCounter: Int = 0
        private set
    override suspend fun get(): T = dataSource().also {
        callCounter++
    }
}