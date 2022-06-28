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
import spacexdemo.domain.dto.Launch

class MainViewModel(
    private val companyInfoUseCase: CompanyInfoUseCase,
    private val launchesUseCase: LaunchesUseCase
) : ViewModel() {

    private val _companyInfo = MutableLiveData<List<Launch>>()
    val companyInfo: LiveData<List<Launch>>
        get() = _companyInfo

    fun loadList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _companyInfo.postValue(launchesUseCase.getLaunches())
            }
        }
    }
}