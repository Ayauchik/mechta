package kz.petprojects.mechta.ui.di

import kz.petprojects.mechta.ui.smartphone.SmartphoneScreenViewModel
import kz.petprojects.mechta.ui.smartphoneDetails.SmartphoneDetailsViewModel
import org.koin.dsl.module


val viewModelModule = module {
    factory { SmartphoneScreenViewModel(get()) }
    factory { SmartphoneDetailsViewModel(get()) }
}