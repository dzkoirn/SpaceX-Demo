package network.api

import io.ktor.client.HttpClient
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SpacexApiServiceImplTest {

    val httpClient: HttpClient = HttpClient()
    val testService = SpacexApiServiceImpl(httpClient)

    @Test
    fun `test receiving all launches api works`() {
        runBlocking {
            val result = testService.all()
            assertTrue("returned result is not empty") { result.isNotEmpty() }
        }


//        assertFalse("returned list is empty") {
//            result.isEmpty()
//        }
    }
}