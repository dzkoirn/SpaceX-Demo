package spacexdemo.domain.api

interface Repo<T> {
    suspend fun get(): T
}