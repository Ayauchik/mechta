package kz.petprojects.mechta.data.di

import kz.petprojects.mechta.data.repository.SmartphonesRepositoryImpl
import kz.petprojects.mechta.domain.repository.SmartphonesRepository
import org.koin.dsl.module


val repositoryModule = module {
    factory<SmartphonesRepository> { SmartphonesRepositoryImpl(get(), get(), get()) }
}