package kz.petprojects.mechta.domain.repository

import kz.petprojects.mechta.domain.model.getSmartphones.Smartphone

interface SmartphonesRepository {
    suspend fun getSmartphones(
        page: Int = 1,
        pageLimit: Int = 20,
        section: String = "smartfony"
    ): List<Smartphone>
}