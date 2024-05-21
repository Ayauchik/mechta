package kz.petprojects.mechta.data.di

import kz.petprojects.mechta.data.use_cases.GetSmartphoneDetailsUseCaseImpl
import kz.petprojects.mechta.data.use_cases.GetSmartphonesUseCaseImpl
import kz.petprojects.mechta.domain.use_cases.GetSmartphoneDetailsUseCase
import kz.petprojects.mechta.domain.use_cases.GetSmartphonesUseCase
import org.koin.dsl.module


val useCaseModule = module {
    factory<GetSmartphonesUseCase> { GetSmartphonesUseCaseImpl(get()) }
    factory<GetSmartphoneDetailsUseCase> { GetSmartphoneDetailsUseCaseImpl(get()) }
}