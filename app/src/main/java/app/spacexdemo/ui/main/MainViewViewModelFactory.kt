package app.spacexdemo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.koin.core.Koin
import org.koin.core.error.NoBeanDefFoundException
import org.koin.dsl.module

val mainScreenModule = module {
    factory { MainViewModel(get(), get()) }
}

class MainViewViewModelFactory(
    private val di: Koin
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return try {
            di.get(modelClass.kotlin)
        } catch (ex: NoBeanDefFoundException) {
            throw NotImplementedError("ViewModel for ${modelClass.simpleName} is not implemented")
        }
    }
}