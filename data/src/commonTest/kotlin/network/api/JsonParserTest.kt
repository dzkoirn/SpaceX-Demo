package network.api

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import spacexdemo.domain.dto.Launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse

/**
 * Test to parse responses with Json Parser
 */
class JsonParserTest {

    private lateinit var json: Json

    @BeforeTest
    fun init() {
        json = provideJsonParser()
    }

    @Test
    fun `parse all response`() {
        val source = javaClass.classLoader.getResourceAsStream("all_response.json")
        val jsonString = BufferedReader(InputStreamReader(source, StandardCharsets.UTF_8))
            .readText()
        val result = json.decodeFromString<List<Launch>>(jsonString)
        assertFalse("should don be empty") { result.isEmpty() }
        assertEquals(4, result.size, "should contain 5 elements")
        assertContentEquals(result.map { it.name }.toTypedArray(), arrayOf("FalconSat", "DemoSat", "Trailblazer", "RatSat"))
    }
}