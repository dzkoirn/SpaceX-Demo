package network.api

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import spacexdemo.domain.dto.CompanyInfo
import spacexdemo.domain.dto.Headquarters
import spacexdemo.domain.dto.Launch
import spacexdemo.domain.dto.Links
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
        val result = json.decodeFromString<List<Launch>>(readJsonAsString("all_response.json"))
        assertFalse("should don be empty") { result.isEmpty() }
        assertEquals(4, result.size, "should contain 5 elements")
        assertContentEquals(result.map { it.name }.toTypedArray(), arrayOf("FalconSat", "DemoSat", "Trailblazer", "RatSat"))
    }

    @Test
    fun `parse company info`() {
        val expected = CompanyInfo(
            name = "SpaceX",
            founder = "Elon Musk",
            founded = 2002,
            employees = 9500,
            vehicles = 4,
            launchSites = 3,
            testSites = 3,
            ceo = "Elon Musk",
            cto = "Elon Musk",
            coo = "Gwynne Shotwell",
            ctoPropulsion = "Tom Mueller",
            valuation = 74000000000,
            summary = "SpaceX designs, manufactures and launches advanced rockets and spacecraft. The company was founded in 2002 to revolutionize space technology, with the ultimate goal of enabling people to live on other planets.",
            headquarters = Headquarters(
                address = "Rocket Road",
                city = "Hawthorne",
                state = "California"
            ),
            links = Links(
                website = "https://www.spacex.com/",
                flickr = "https://www.flickr.com/photos/spacex/",
                twitter = "https://twitter.com/SpaceX",
                elonTwitter = "https://twitter.com/elonmusk"
            )
        )
        val result = json.decodeFromString<CompanyInfo>(readJsonAsString("company_info.json"))
        assertEquals(expected, result)
    }

    private fun readJsonAsString(name: String): String {
        val source = javaClass.classLoader.getResourceAsStream(name)
        return BufferedReader(InputStreamReader(source, StandardCharsets.UTF_8))
            .readText()
    }
}