package app.spacexdemo.ui.main

import spacexdemo.domain.dto.CompanyInfo
import spacexdemo.domain.dto.LaunchInfo

sealed class MainScreenState {
    object Loading: MainScreenState()

    data class ScreenData(
        val companyInfo: CompanyInfo,
        val list: List<LaunchInfo>
    ) : MainScreenState()

    object LoadingError: MainScreenState()
}


