package app.spacexdemo.di

import network.api.provideHttpClient
import network.api.SpacexApiService
import storage.CachedRepo
import spacexdemo.domain.CompanyInfoUseCase
import spacexdemo.domain.LaunchesUseCase


/**
 * Because we have very simple application let's use own service container without any libraries and frameworks
 */
object ServiceContainer {

    private val httpClient by lazy { provideHttpClient() }

    private val spacexApiService by lazy { SpacexApiService(httpClient) }

    private val launchesRepo by lazy { CachedRepo { spacexApiService.all() } }

    private val companyInfoRepo by lazy { CachedRepo { spacexApiService.company() } }

    fun provideCompanyInfoUseCase(): CompanyInfoUseCase = CompanyInfoUseCase(companyInfoRepo)

    fun provideLaunchesUseCase(): LaunchesUseCase = LaunchesUseCase(launchesRepo)
}