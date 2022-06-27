package storage

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CachedRepoTest {

    class FakeData

    class StubDataSource {

        var counter: Int = 0

        fun get(): FakeData {
            return FakeData().also {
                counter++
            }
        }
    }

    private lateinit var stubDataSource: StubDataSource

    @BeforeEach
    fun setup() {
        stubDataSource = StubDataSource()
    }

    @Test
    fun `test that StubDataSource actually count calls`() {
        repeat(5) {
            stubDataSource.get()
        }
        assertEquals(5, stubDataSource.counter)
    }

    @Test
    fun `test that data actually cached`() {
        val testRepo = CachedRepo(stubDataSource::get)
        runBlocking {
            repeat(5) {
                testRepo.get()
            }
        }
        assertEquals(1, stubDataSource.counter)
    }
}