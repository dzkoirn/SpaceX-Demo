package app.spacexdemo.ui.main

import androidx.lifecycle.ViewModel
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import spacexdemo.domain.CompanyInfoUseCase
import spacexdemo.domain.LaunchesUseCase
import spacexdemo.domain.api.Repo
import spacexdemo.domain.dto.CompanyInfo
import spacexdemo.domain.dto.Launch

class MainViewViewModelFactoryTest {

    private val testModule by lazy {
        module {
            val infoRepo = object : Repo<CompanyInfo> {
                override suspend fun get(): CompanyInfo {
                    TODO("Not yet implemented")
                }
            }
            val launchRepo = object : Repo<List<Launch>> {
                override suspend fun get(): List<Launch> {
                    TODO("Not yet implemented")
                }
            }
            factory { CompanyInfoUseCase(infoRepo) }
            factory { LaunchesUseCase(launchRepo) }
            factory { MainViewModel(get(), get()) }
        }
    }
    private val testKoin = koinApplication {
        modules(testModule)
    }.koin

    private val testViewModelFactory = MainViewViewModelFactory(testKoin)

    @Test
    fun shouldProvideViewModel() {
        assertNotNull(testViewModelFactory.create(MainViewModel::class.java))
    }

    @Test(expected = NotImplementedError::class)
    fun shouldThrowExeption() {
        class TestViewModel : ViewModel()
        testViewModelFactory.create(TestViewModel::class.java)
    }
}