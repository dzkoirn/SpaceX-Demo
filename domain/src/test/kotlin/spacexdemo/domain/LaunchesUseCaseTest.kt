package spacexdemo.domain

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Test
import spacexdemo.domain.dto.*
import java.time.*

class LaunchesUseCaseTest {

    private val fakeLaunches = listOf(
        Launch(
            name = "mission 1",
            success = true,
            details = "mission 1",
            rocket = "rocket 1",
            date_unix = LocalDate.of(2006, Month.JANUARY, 1).toEpochDay() * 86400L,
            date_utc = "2006-01-01T00:00:00+00:00",
            upcoming = false
        ),
        Launch(
            name = "mission 2",
            success = false,
            details = "mission 2",
            rocket = "rocket 2",
            date_unix = LocalDate.of(2010, Month.JUNE, 21).toEpochDay() * 86400L,
            date_utc = "2010-06-21T12:00:00+00:00",
            upcoming = false
        ),
        Launch(
            name = "mission 3",
            success = true,
            details = "mission 3",
            rocket = "rocket 3",
            date_unix = LocalDate.of(2020, Month.AUGUST, 10).toEpochDay() * 86400L,
            date_utc = "2020-08-10T12:00:00+00:00",
            upcoming = false
        ),
        Launch(
            name = "mission 4",
            success = false,
            details = "mission 4",
            rocket = "rocket 1",
            date_unix = LocalDate.of(2022, Month.JULY, 1).toEpochDay() * 86400L,
            date_utc = "2022-07-01T12:00:00+00:00",
            upcoming = false
        ),
        Launch(
            name = "mission 5",
            success = true,
            details = "mission 5",
            rocket = "rocket 2",
            date_unix = LocalDate.of(2023, Month.MAY, 1).toEpochDay() * 86400L,
            date_utc = "2023-05-01T12:00:00+00:00",
            upcoming = false
        ),
    )

    private val fakeRockets = listOf(
        Rocket(id = "rocket 1", name = "Rocket 1", type = "rocket"),
        Rocket(id = "rocket 2", name = "Rocket 2", type = "rocket"),
        Rocket(id = "rocket 3", name = "Rocket 3", type = "rocket"),
    )

    @Test
    fun `test that use case call Repos actually`() {
        val fakeLaunchesRepo = TestRepo { fakeLaunches }
        val fakeRocketRepo = TestRepo { fakeRockets }
        val fakeSettingsRepo = TestRepo { FilterSettings.DEFAULT }
        val testUseCase = LaunchesUseCase(fakeLaunchesRepo, fakeRocketRepo, fakeSettingsRepo)
        runBlocking {
            testUseCase.getLaunches()

            assertEquals(1, fakeLaunchesRepo.callCounter)
            assertEquals(1, fakeRocketRepo.callCounter)
            assertEquals(1, fakeSettingsRepo.callCounter)
        }
    }

    @Test
    fun `test with default filtering setting`() {
        val fakeLaunchesRepo = TestRepo { fakeLaunches }
        val fakeRocketRepo = TestRepo { fakeRockets }
        val fakeSettingsRepo = TestRepo {
            FilterSettings(
                IntRange(2006, 2024),
                LaunchSuccess.ALL,
                SortOrder.ASC
            )
        }

        val expected = listOf(
            LaunchInfo(
                fakeLaunches[0],
                fakeRockets[0]
            ),
            LaunchInfo(
                fakeLaunches[1],
                fakeRockets[1]
            ),
            LaunchInfo(
                fakeLaunches[2],
                fakeRockets[2]
            ),
            LaunchInfo(
                fakeLaunches[3],
                fakeRockets[0]
            ),
            LaunchInfo(
                fakeLaunches[4],
                fakeRockets[1]
            ),
        )

        val testUseCase = LaunchesUseCase(fakeLaunchesRepo, fakeRocketRepo, fakeSettingsRepo)
        runBlocking {
            val result = testUseCase.getLaunches()

            assertIterableEquals(expected, result)
        }
    }

    @Test
    fun `test with only success flight`() {
        val fakeLaunchesRepo = TestRepo { fakeLaunches }
        val fakeRocketRepo = TestRepo { fakeRockets }
        val fakeSettingsRepo = TestRepo {
            FilterSettings(
                IntRange(2006, 2024),
                LaunchSuccess.SUCCESSFULL,
                SortOrder.ASC
            )
        }

        val expected = listOf(
            LaunchInfo(
                fakeLaunches[0],
                fakeRockets[0]
            ),
            LaunchInfo(
                fakeLaunches[2],
                fakeRockets[2]
            ),
            LaunchInfo(
                fakeLaunches[4],
                fakeRockets[1]
            ),
        )

        val testUseCase = LaunchesUseCase(fakeLaunchesRepo, fakeRocketRepo, fakeSettingsRepo)
        runBlocking {
            val result = testUseCase.getLaunches()

            assertIterableEquals(expected, result)
        }
    }

    @Test
    fun `test with only failed flight`() {
        val fakeLaunchesRepo = TestRepo { fakeLaunches }
        val fakeRocketRepo = TestRepo { fakeRockets }
        val fakeSettingsRepo = TestRepo {
            FilterSettings(
                IntRange(2006, 2024),
                LaunchSuccess.FAILED,
                SortOrder.ASC
            )
        }

        val expected = listOf(
            LaunchInfo(
                fakeLaunches[1],
                fakeRockets[1]
            ),
            LaunchInfo(
                fakeLaunches[3],
                fakeRockets[0]
            ),
        )

        val testUseCase = LaunchesUseCase(fakeLaunchesRepo, fakeRocketRepo, fakeSettingsRepo)
        runBlocking {
            val result = testUseCase.getLaunches()

            assertIterableEquals(expected, result)
        }
    }

    @Test
    fun `test with DESC order`() {
        val fakeLaunchesRepo = TestRepo { fakeLaunches }
        val fakeRocketRepo = TestRepo { fakeRockets }
        val fakeSettingsRepo = TestRepo {
            FilterSettings(
                IntRange(2006, 2024),
                LaunchSuccess.ALL,
                SortOrder.DESC
            )
        }

        val expected = listOf(
            LaunchInfo(
                fakeLaunches[4],
                fakeRockets[1]
            ),
            LaunchInfo(
                fakeLaunches[3],
                fakeRockets[0]
            ),
            LaunchInfo(
                fakeLaunches[2],
                fakeRockets[2]
            ),
            LaunchInfo(
                fakeLaunches[1],
                fakeRockets[1]
            ),
            LaunchInfo(
                fakeLaunches[0],
                fakeRockets[0]
            ),
        )

        val testUseCase = LaunchesUseCase(fakeLaunchesRepo, fakeRocketRepo, fakeSettingsRepo)
        runBlocking {
            val result = testUseCase.getLaunches()

            assertIterableEquals(expected, result)
        }
    }

    @Test
    fun `test with range`() {
        val fakeLaunchesRepo = TestRepo { fakeLaunches }
        val fakeRocketRepo = TestRepo { fakeRockets }
        val fakeSettingsRepo = TestRepo {
            FilterSettings(
                IntRange(2010, 2023),
                LaunchSuccess.ALL,
                SortOrder.ASC
            )
        }

        val expected = listOf(
            LaunchInfo(
                fakeLaunches[1],
                fakeRockets[1]
            ),
            LaunchInfo(
                fakeLaunches[2],
                fakeRockets[2]
            ),
            LaunchInfo(
                fakeLaunches[3],
                fakeRockets[0]
            ),
        )

        val testUseCase = LaunchesUseCase(fakeLaunchesRepo, fakeRocketRepo, fakeSettingsRepo)
        runBlocking {
            val result = testUseCase.getLaunches()

            assertIterableEquals(expected, result)
        }
    }
}