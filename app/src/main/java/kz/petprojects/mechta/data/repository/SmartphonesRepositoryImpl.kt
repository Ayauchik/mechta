package kz.petprojects.mechta.data.repository

import kz.petprojects.mechta.data.network.api.PlaceholderService
import kz.petprojects.mechta.data.network.mapper.getSmartphones.SmartphonesMapper
import kz.petprojects.mechta.domain.model.getSmartphones.Smartphone
import kz.petprojects.mechta.domain.repository.SmartphonesRepository

class SmartphonesRepositoryImpl(
    private val placeholderService: PlaceholderService,
    private val smartphonesMapper: SmartphonesMapper

) : SmartphonesRepository {
    override suspend fun getSmartphones(
        page: Int,
        pageLimit: Int,
        section: String
    ): List<Smartphone> {
        return smartphonesMapper.fromRemoteToDomain(
            placeholderService.getSmartphones(
                page, pageLimit, section
            )
        )
    }
}