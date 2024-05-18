package kz.petprojects.mechta.data.network.mapper.getSmartphones

import kz.petprojects.mechta.data.network.response.getSmartphones.GetSmartphonesResponse
import kz.petprojects.mechta.domain.model.getSmartphones.Smartphone

class SmartphonesMapper(
    private val itemMapper: ItemMapper
) {
    fun fromRemoteToDomain(smartphones: GetSmartphonesResponse): List<Smartphone>{
        return smartphones.data.items.map {
            itemMapper.fromRemoteToDomain(it)
        }
    }
}