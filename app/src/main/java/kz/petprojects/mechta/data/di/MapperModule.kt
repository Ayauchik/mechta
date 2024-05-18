package kz.petprojects.mechta.data.di

import kz.petprojects.mechta.data.network.mapper.getSmartphones.ItemMapper
import kz.petprojects.mechta.data.network.mapper.getSmartphones.SmartphonesMapper
import org.koin.dsl.module


val mapperModule = module{
    factory { ItemMapper() }
    factory { SmartphonesMapper(get()) }
}