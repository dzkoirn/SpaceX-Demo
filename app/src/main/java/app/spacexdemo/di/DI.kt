package app.spacexdemo.di

import app.spacexdemo.ui.main.mainScreenModule
import network.api.SpacexApiService
import network.api.provideHttpClient
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import spacexdemo.domain.CompanyInfoUseCase
import spacexdemo.domain.LaunchesUseCase
import storage.CachedRepo
import storage.CompanyInfoRepo
import storage.LaunchesRepo
import storage.RocketsRepo

object DI : KoinComponent {

    private val appModule by lazy {
        module {
            factory { provideHttpClient() }
            factory { SpacexApiService(get()) }
            single { LaunchesRepo(get()) }
            single { RocketsRepo(get()) }
            single { CompanyInfoRepo(get()) }
            factory { LaunchesUseCase(get<LaunchesRepo>(), get<RocketsRepo>()) }
            factory { CompanyInfoUseCase(get<CompanyInfoRepo>()) }
        }
    }

    private val _koin by lazy {
        koinApplication {
            modules(appModule)
            modules(mainScreenModule)
        }.koin
    }

    override fun getKoin(): Koin = _koin
}