package spacexdemo.domain.api

interface MutableRepo<T> : Repo<T> {
    suspend fun set(value: T)
}