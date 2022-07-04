package spacexdemo.domain.dto

data class FilterSettings(
    /**
     * Range in seconds from start year to end year
     */
    val yearsRange: IntRange,
    val launchSuccess: LaunchSuccess,
    val sortOrder: SortOrder
) {
    companion object {
        val DEFAULT = FilterSettings(
            yearsRange = IntRange(
                // From January 2006
                2006,
                // To January 2026
                2026
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
