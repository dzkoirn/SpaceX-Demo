package spacexdemo.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.time.ZoneId

class UtilsTest {

    @ParameterizedTest
    @MethodSource("testData")
    fun test(testData: Pair<Int, Long>) {
        assertEquals(testData.second, yearToEpochSeconds(testData.first, ZoneId.of("UTC")))
    }

    companion object {

        @JvmStatic
        fun testData() = listOf(
            2006 to 1136073600L,
            2016 to 1451606400L,
            2020 to 1577836800L,
            2022 to 1640995200
        )

    }


}