package network.api

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Note!!! This tests use real API and do real request and handle real responces
 */
class SpacexApiServiceImplTest {

    private val testService = SpacexApiService(provideHttpClient())

    @Test
    fun `test receiving all launches api works`() {
        runBlocking {
            val result = testService.all()
            assertTrue({ result.isNotEmpty() }, "returned result is not empty")
        }
    }

    @Test
    fun `test company info api works`() {
        runBlocking {
            val result = testService.company()
            assertNotNull(result, "returned result is present")
        }
    }
}