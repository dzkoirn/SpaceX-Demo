package app.spacexdemo.ui.main

import org.koin.dsl.module

val mainScreenModule = module {
    factory { MainViewModel(get(), get()) }
}