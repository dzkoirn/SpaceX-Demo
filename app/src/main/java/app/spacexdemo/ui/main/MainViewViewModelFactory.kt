package app.spacexdemo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.spacexdemo.di.ServiceContainer

class MainViewViewModelFactory(
    val serviceContainer: ServiceContainer
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(serviceContainer.provideCompanyInfoUseCase(), serviceContainer.provideLaunchesUseCase()) as T
    }
}