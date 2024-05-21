package kz.petprojects.mechta.domain.use_cases

import kz.petprojects.mechta.domain.model.SmartphoneDetails

interface GetSmartphoneDetailsUseCase {
    suspend fun invoke(code: String): SmartphoneDetails
}