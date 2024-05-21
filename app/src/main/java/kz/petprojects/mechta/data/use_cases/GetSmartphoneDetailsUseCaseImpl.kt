package kz.petprojects.mechta.data.use_cases

import kz.petprojects.mechta.domain.model.SmartphoneDetails
import kz.petprojects.mechta.domain.repository.SmartphonesRepository
import kz.petprojects.mechta.domain.use_cases.GetSmartphoneDetailsUseCase

class GetSmartphoneDetailsUseCaseImpl(
    private val smartphonesRepository: SmartphonesRepository
): GetSmartphoneDetailsUseCase {
    override suspend fun invoke(code: String): SmartphoneDetails {
        return smartphonesRepository.getSmartphoneDetails(code)
    }
}