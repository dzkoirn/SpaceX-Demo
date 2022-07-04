package app.spacexdemo.ui.filter

import org.koin.dsl.module

val filterScreenModule = module {
    factory { FilterViewModel(get()) }
}