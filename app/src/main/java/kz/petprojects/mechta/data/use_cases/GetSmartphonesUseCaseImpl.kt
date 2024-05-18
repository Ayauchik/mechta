package kz.petprojects.mechta.data.use_cases

import kz.petprojects.mechta.domain.model.getSmartphones.Smartphone
import kz.petprojects.mechta.domain.repository.SmartphonesRepository
import kz.petprojects.mechta.domain.use_cases.GetSmartphonesUseCase

class GetSmartphonesUseCaseImpl(
    private val smartphonesRepository: SmartphonesRepository

): GetSmartphonesUseCase {
    override suspend fun invoke(page: Int, pageLimit: Int, section: String): List<Smartphone> {
        return smartphonesRepository.getSmartphones(page, pageLimit, section)
    }
}