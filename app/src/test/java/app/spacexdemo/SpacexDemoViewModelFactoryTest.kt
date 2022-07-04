package app.spacexdemo

import androidx.lifecycle.ViewModel
import app.spacexdemo.di.DI
import app.spacexdemo.ui.filter.FilterViewModel
import app.spacexdemo.ui.main.MainViewModel
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import spacexdemo.domain.CompanyInfoUseCase
import spacexdemo.domain.LaunchesUseCase
import spacexdemo.domain.api.Repo
import spacexdemo.domain.dto.CompanyInfo
import spacexdemo.domain.dto.FilterSettings
import spacexdemo.domain.dto.Launch
import spacexdemo.domain.dto.Rocket

class SpacexDemoViewModelFactoryTest {

    private val testKoin = DI.getKoin()

    private val testViewModelFactory = SpacexDemoViewModelFactory(testKoin)

    @Test
    fun shouldProvideMainViewModel() {
        assertNotNull(testViewModelFactory.create(MainViewModel::class.java))
    }

    @Test
    fun shouldProvideFilterViewModel() {
        assertNotNull(testViewModelFactory.create(FilterViewModel::class.java))
    }

    @Test(expected = NotImplementedError::class)
    fun shouldThrowExeption() {
        class TestViewModel : ViewModel()
        testViewModelFactory.create(TestViewModel::class.java)
    }
}