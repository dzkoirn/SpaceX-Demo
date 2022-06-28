package app.spacexdemo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import spacexdemo.domain.CompanyInfoUseCase
import spacexdemo.domain.LaunchesUseCase

class MainViewModel(
    private val companyInfoUseCase: CompanyInfoUseCase,
    private val launchesUseCase: LaunchesUseCase
) : ViewModel() {

    private val _state = MutableLiveData<MainScreenState>()
    val screenState: LiveData<MainScreenState>
        get() = _state

    fun loadData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _state.postValue(MainScreenState.Loading)
                _state.postValue(MainScreenState.ScreenData(
                    companyInfoUseCase.getCompanyInfo(),
                    launchesUseCase.getLaunches()
                ))
            }
        }
    }
}