package spacexdemo.domain.dto

import java.time.*

data class FilterSettings(
    /**
     * Range in seconds from start year to end year
     */
    val filterRange: LongRange,
    val launchSuccess: LaunchSuccess,
    val sortOrder: SortOrder
) {
    companion object {
        val DEFAULT = FilterSettings(
            filterRange = LongRange(
                // From January 2006
                LocalDate.of(2006, Month.JANUARY, 1).toEpochDay() * 86400L,
                // Current moment + 2 year
                LocalDate.now().plusYears(2).toEpochDay() * 86400L
            ),
            launchSuccess = LaunchSuccess.ALL,
            sortOrder = SortOrder.ASC
        )
    }
}

enum class LaunchSuccess {
    ALL, SUCCESSFULL, FAILED
}

enum class SortOrder {
    ASC, DESC
}
