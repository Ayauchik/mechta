package kz.petprojects.mechta.data.repository

import kz.petprojects.mechta.data.network.api.PlaceholderService
import kz.petprojects.mechta.data.network.mapper.getSmartphoneDetails.SmartphoneDetailsMapper
import kz.petprojects.mechta.data.network.mapper.getSmartphones.SmartphonesMapper
import kz.petprojects.mechta.domain.model.SmartphoneDetails
import kz.petprojects.mechta.domain.model.getSmartphones.Smartphone
import kz.petprojects.mechta.domain.repository.SmartphonesRepository

class SmartphonesRepositoryImpl(
    private val placeholderService: PlaceholderService,
    private val smartphonesMapper: SmartphonesMapper,
    private val smartphoneDetailsMapper: SmartphoneDetailsMapper

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

    override suspend fun getSmartphoneDetails(code: String): SmartphoneDetails {
        return smartphoneDetailsMapper.fromRemoteToDomain(
            placeholderService.getSmartphoneDetails(code)
        )
    }
}