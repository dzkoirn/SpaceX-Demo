package app.spacexdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.koin.core.Koin
import org.koin.core.error.NoBeanDefFoundException

class SpacexDemoViewModelFactory(
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