package app.spacexdemo.ui.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import spacexdemo.domain.FilterUseCase
import spacexdemo.domain.dto.FilterSettings

class FilterViewModel(
    private val filterUseCase: FilterUseCase
) : ViewModel() {

    private val _state = MutableLiveData<FilterState>()
    val state: LiveData<FilterState>
        get() = _state

    fun loadData() {
        viewModelScope.launch {
            _state.postValue(map2FilterState(filterUseCase.getSettings()))
        }
    }

    private fun map2FilterState(value: FilterSettings): FilterState =
        FilterState(
            startYear = value.yearsRange.first.toFloat(),
            endYear = value.yearsRange.last.toFloat(),
            launchSuccess = value.launchSuccess,
            sortOrder = value.sortOrder
        )

    fun save(filterState: FilterState) {
        viewModelScope.launch {
            filterUseCase.saveSetting(
                FilterSettings(
                    yearsRange = IntRange(filterState.startYear.toInt(), filterState.endYear.toInt()),
                    launchSuccess = filterState.launchSuccess,
                    sortOrder = filterState.sortOrder
                )
            )
        }
    }

}