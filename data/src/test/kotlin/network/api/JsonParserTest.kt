package network.api

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import spacexdemo.domain.dto.*
import spacexdemo.domain.dto.CompanyInfo.Headquarters
import spacexdemo.domain.dto.CompanyInfo.Links
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * Test to parse responses with Json Parser
 */
class JsonParserTest {

    private lateinit var json: Json

    @BeforeEach
    fun init() {
        json = provideJsonParser()
    }

    @Test
    fun `parse all response`() {
        val result = json.decodeFromString<List<Launch>>(readJsonAsString("all_response.json"))
        assertFalse({ result.isEmpty() }, "should don be empty")
        assertEquals(4, result.size, "should contain 5 elements")
        assertArrayEquals(result.map { it.name }.toTypedArray(), arrayOf("FalconSat", "DemoSat", "Trailblazer", "RatSat"))
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

    @Test
    fun `parse rockets`() {
        val expected = listOf(
            Rocket(
                id = "5e9d0d95eda69955f709d1eb",
                name = "Falcon 1",
                type = "rocket",
            ),
            Rocket(
                id= "5e9d0d95eda69973a809d1ec",
                name = "Falcon 9",
                type =  "rocket",
            ),
            Rocket(
                id = "5e9d0d95eda69974db09d1ed",
                name = "Falcon Heavy",
                type = "rocket",
            ),
            Rocket(
                id = "5e9d0d96eda699382d09d1ee",
                name = "Starship",
                type = "rocket",
            )
        )
        val result = json.decodeFromString<List<Rocket>>(readJsonAsString("rockets.json"))
        assertIterableEquals(expected, result)
    }

    private fun readJsonAsString(name: String): String {
        val source = javaClass.classLoader.getResourceAsStream(name)
        return BufferedReader(InputStreamReader(source, StandardCharsets.UTF_8))
            .readText()
    }
}