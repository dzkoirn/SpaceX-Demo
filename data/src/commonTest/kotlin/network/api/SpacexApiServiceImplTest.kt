package network.api

import io.ktor.client.HttpClient
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Note!!! This tests use real API and do real request and handle real responces
 */
class SpacexApiServiceImplTest {

    private val httpClient: HttpClient = HttpClient()
    private val testService = SpacexApiServiceImpl(httpClient)

    @Test
    fun `test receiving all launches api works`() {
        runBlocking {
            val result = testService.all()
            assertTrue("returned result is not empty") { result.isNotEmpty() }
        }
    }
}