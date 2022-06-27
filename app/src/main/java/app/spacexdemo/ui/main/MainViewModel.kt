package app.spacexdemo.ui.main

import androidx.lifecycle.ViewModel

import spacexdemo.domain.CompanyInfoUseCase
import spacexdemo.domain.LaunchesUseCase

class MainViewModel(
    private val companyInfoUseCase: CompanyInfoUseCase,
    private val launchesUseCase: LaunchesUseCase
) : ViewModel() {
    // TODO: Implement the ViewModel
}