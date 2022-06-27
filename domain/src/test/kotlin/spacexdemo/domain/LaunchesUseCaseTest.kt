package spacexdemo.domain

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import spacexdemo.domain.dto.Launch

class LaunchesUseCaseTest {

    val fakeLaunches = listOf(
        Launch(name = "mission 1"),
        Launch(name = "mission 2"),
        Launch(name = "mission 3"),
        Launch(name = "mission 4"),
        Launch(name = "mission 5"),
    )

    @Test
    fun `test that use case call Repo actually`() {
        val fakeRepo = TestRepo { fakeLaunches }
        val testUseCase = LaunchesUseCase(fakeRepo)
        runBlocking {
            val result = testUseCase.getLaunches()
            assertEquals(1, fakeRepo.callCounter)
            assertEquals(fakeLaunches, result)
        }
    }
}