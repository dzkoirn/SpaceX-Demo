package spacexdemo.domain

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import spacexdemo.domain.dto.CompanyInfo
import spacexdemo.domain.dto.CompanyInfo.Headquarters
import spacexdemo.domain.dto.CompanyInfo.Links

class CompanyInfoUseCaseTest {

    private val fakeCompanyInfo = CompanyInfo(
        name ="Fake Inc.",
        founder = "Founder Founder",
        founded = 2000,
        employees = 1_000_000,
        launchSites = 100500,
        vehicles = 100500,
        testSites = 1024,
        ceo = "Ceo Ceo",
        cto = "Cto Cto",
        coo = "Coo Coo",
        ctoPropulsion = "",
        valuation = 1000,
        headquarters = Headquarters(
            address = "null",
            city = "null",
            state = "null"
        ),
        links = Links(
            website = "fake.org.com",
            flickr = "#fake",
            twitter = "@fake",
            elonTwitter = "@elonTwitter"
        ),
        summary = "Fake object for testing"
    )

    @Test
    fun `test that use case call Repo actually`() {
        val fakeRepo = TestRepo { fakeCompanyInfo }
        val testUseCase = CompanyInfoUseCase(fakeRepo)
        runBlocking {
            val result = testUseCase.getCompanyInfo()
            assertEquals(1, fakeRepo.callCounter)
            assertEquals(fakeCompanyInfo, result)
        }
    }
}