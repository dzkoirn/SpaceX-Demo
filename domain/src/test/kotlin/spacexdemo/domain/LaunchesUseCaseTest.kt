package spacexdemo.domain

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Test
import spacexdemo.domain.dto.Launch
import spacexdemo.domain.dto.LaunchInfo
import spacexdemo.domain.dto.Rocket

class LaunchesUseCaseTest {

    private val fakeLaunches = listOf(
        Launch(name = "mission 1", success = true, details = "mission 1", rocket = "rocket 1", date_unix = 1656451549, date_utc = "2022-06-28T21:25:49+00:00", upcoming = false),
        Launch(name = "mission 2", success = true, details = "mission 2", rocket = "rocket 2", date_unix = 1656451549, date_utc = "2022-06-28T21:25:49+00:00", upcoming = false),
        Launch(name = "mission 3", success = true, details = "mission 3", rocket = "rocket 3", date_unix = 1656451549, date_utc = "2022-06-28T21:25:49+00:00", upcoming = false),
        Launch(name = "mission 4", success = true, details = "mission 4", rocket = "rocket 1", date_unix = 1656451549, date_utc = "2022-06-28T21:25:49+00:00", upcoming = false),
        Launch(name = "mission 5", success = true, details = "mission 5", rocket = "rocket 2", date_unix = 1656451549, date_utc = "2022-06-28T21:25:49+00:00", upcoming = false),
    )

    private val fakeRockets = listOf(
        Rocket(id = "rocket 1", name = "Rocket 1", type = "rocket"),
        Rocket(id = "rocket 2", name = "Rocket 2", type = "rocket"),
        Rocket(id = "rocket 3", name = "Rocket 3", type = "rocket"),
    )

    private val expected = listOf(
        LaunchInfo(
            Launch(name = "mission 1", success = true, details = "mission 1", rocket = "rocket 1", date_unix = 1656451549, date_utc = "2022-06-28T21:25:49+00:00", upcoming = false),
            Rocket(id = "rocket 1", name = "Rocket 1", type = "rocket")
        ),
        LaunchInfo(
            Launch(name = "mission 2", success = true, details = "mission 2", rocket = "rocket 2", date_unix = 1656451549, date_utc = "2022-06-28T21:25:49+00:00", upcoming = false),
            Rocket(id = "rocket 2", name = "Rocket 2", type = "rocket")
        ),
        LaunchInfo(
            Launch(name = "mission 3", success = true, details = "mission 3", rocket = "rocket 3", date_unix = 1656451549, date_utc = "2022-06-28T21:25:49+00:00", upcoming = false),
            Rocket(id = "rocket 3", name = "Rocket 3", type = "rocket")
        ),
        LaunchInfo(
            Launch(name = "mission 4", success = true, details = "mission 4", rocket = "rocket 1", date_unix = 1656451549, date_utc = "2022-06-28T21:25:49+00:00", upcoming = false),
            Rocket(id = "rocket 1", name = "Rocket 1", type = "rocket")
        ),
        LaunchInfo(
            Launch(name = "mission 5", success = true, details = "mission 5", rocket = "rocket 2", date_unix = 1656451549, date_utc = "2022-06-28T21:25:49+00:00", upcoming = false),
            Rocket(id = "rocket 2", name = "Rocket 2", type = "rocket")
        ),
    )

    @Test
    fun `test that use case call Repos actually`() {
        val fakeLaunchesRepo = TestRepo { fakeLaunches }
        val fakeRocketRepo = TestRepo { fakeRockets }
        val testUseCase = LaunchesUseCase(fakeLaunchesRepo, fakeRocketRepo)
        runBlocking {
            testUseCase.getLaunches()

            assertEquals(1, fakeLaunchesRepo.callCounter)
            assertEquals(1, fakeRocketRepo.callCounter)
        }
    }

    @Test
    fun `test that use case call Repo actually`() {
        val fakeLaunchesRepo = TestRepo { fakeLaunches }
        val fakeRocketRepo = TestRepo { fakeRockets }
        val testUseCase = LaunchesUseCase(fakeLaunchesRepo, fakeRocketRepo)
        runBlocking {
            val result = testUseCase.getLaunches()

            assertIterableEquals(expected, result)
        }
    }
}