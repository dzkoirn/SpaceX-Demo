package app.spacexdemo.di

import app.spacexdemo.ui.filter.FilterViewModel
import app.spacexdemo.ui.main.MainViewModel
import io.ktor.client.*
import network.api.SpacexApiService
import org.junit.Assert.assertNotNull
import org.junit.Test
import spacexdemo.domain.CompanyInfoUseCase
import spacexdemo.domain.FilterUseCase
import spacexdemo.domain.LaunchesUseCase
import storage.CompanyInfoRepo
import storage.LaunchesRepo
import storage.SettingsRepo

/**
 * Because Koin is runtime framework add test to test that DI Tree is valid
 */
class DITest {

    private val koin = DI.getKoin()

    @Test
    fun `HttpClient is not null`() {
        assertNotNull("HttpClient", koin.get<HttpClient>())
    }

    @Test
    fun `SpacexApiService is not null`() {
        assertNotNull("SpacexApiService", koin.get<SpacexApiService>())
    }

    @Test
    fun `LaunchesRepo is not null`() {
        assertNotNull("LaunchesRepo", koin.get<LaunchesRepo>())
    }

    @Test
    fun `CompanyInfoRepo is not null`() {
        assertNotNull("CompanyInfoRepo", koin.get<CompanyInfoRepo>())
    }

    @Test
    fun `CompanyInfoUseCase is not null`() {
        assertNotNull("CompanyInfoUseCase", koin.get<CompanyInfoUseCase>())
    }

    @Test
    fun `LaunchesUseCase is not null`() {
        assertNotNull("LaunchesUseCase", koin.get<LaunchesUseCase>())
    }

    @Test
    fun `MainViewModel is not null`() {
        assertNotNull("MainViewModel", koin.get<MainViewModel>())
    }

    @Test
    fun `FilterViewModel is not null`() {
        assertNotNull("FilterViewModel", koin.get<FilterViewModel>())
    }

    @Test
    fun `SettingsRepo is not null`() {
        assertNotNull("SettingsRepo", koin.get<SettingsRepo>())
    }

    @Test
    fun `FilterUseCase is not null`() {
        assertNotNull("FilterUseCase", koin.get<FilterUseCase>())
    }
}