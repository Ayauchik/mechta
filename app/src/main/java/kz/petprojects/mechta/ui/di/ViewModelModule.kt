package kz.petprojects.mechta.ui.di

import kz.petprojects.mechta.ui.smartphone.SmartphoneScreenViewModel
import org.koin.dsl.module


val viewModelModule = module {
    factory { SmartphoneScreenViewModel(get()) }
}