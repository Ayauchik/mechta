package kz.petprojects.mechta.domain.use_cases

import kz.petprojects.mechta.domain.model.getSmartphones.Smartphone

interface GetSmartphonesUseCase {
    suspend fun invoke(
        page: Int = 1,
        pageLimit: Int = 20,
        section: String = "smartfony"
    ): List<Smartphone>
}