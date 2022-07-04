package app.spacexdemo.ui.filter

import spacexdemo.domain.dto.LaunchSuccess
import spacexdemo.domain.dto.SortOrder

data class FilterState(
    val startYear: Float,
    val endYear: Float,
    val launchSuccess: LaunchSuccess,
    val sortOrder: SortOrder
)
