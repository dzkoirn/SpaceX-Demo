package app.spacexdemo.di

import android.util.Log
import app.spacexdemo.ui.filter.filterScreenModule
import app.spacexdemo.ui.main.mainScreenModule
import network.api.SpacexApiService
import network.api.provideHttpClient
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import spacexdemo.domain.CompanyInfoUseCase
import spacexdemo.domain.FilterUseCase
import spacexdemo.domain.LaunchesUseCase
import storage.*
import kotlin.math.sin

object DI : KoinComponent {

    private val appModule by lazy {
        module {
            factory { provideHttpClient() }
            factory { SpacexApiService(get()) }
            single { LaunchesRepo(get()) }
            single { RocketsRepo(get()) }
            single { CompanyInfoRepo(get()) }
            single { SettingsRepo() }
            factory { LaunchesUseCase(get<LaunchesRepo>(), get<RocketsRepo>(), get<SettingsRepo>()) }
            factory { CompanyInfoUseCase(get<CompanyInfoRepo>()) }
            factory { FilterUseCase(get<SettingsRepo>()) }
        }
    }

    private val _koin by lazy {
        koinApplication {
            modules(appModule)
            modules(mainScreenModule)
            modules(filterScreenModule)
        }.koin
    }

    override fun getKoin(): Koin = _koin
}