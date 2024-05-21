package kz.petprojects.mechta.domain.repository

import kz.petprojects.mechta.domain.model.Smartphone
import kz.petprojects.mechta.domain.model.SmartphoneDetails

interface SmartphonesRepository {
    suspend fun getSmartphones(
        page: Int = 1,
        pageLimit: Int = 20,
        section: String = "smartfony"
    ): List<Smartphone>


    suspend fun getSmartphoneDetails(code: String): SmartphoneDetails
}