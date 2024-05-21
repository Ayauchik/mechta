package kz.petprojects.mechta.data.network.mapper.getSmartphoneDetails

import kz.petprojects.mechta.data.network.response.getSmartphoneDetails.GetSmartphoneDetailsResponse
import kz.petprojects.mechta.domain.model.SmartphoneDetails


class SmartphoneDetailsMapper(
    private val propertiesMapper: PropertiesMapper,
) {

    fun fromRemoteToDomain(response: GetSmartphoneDetailsResponse): SmartphoneDetails {
        return SmartphoneDetails(
            code = response.data.code,
            name = response.data.name,
            photos = response.data.photos,
            rating = response.data.rating,
            reviewsCount = response.data.reviews_count,
            inFavourites = response.data.in_favorites,
            mainProperties = response.data.main_properties.map {
                propertiesMapper.fromRemoteToDomain(it)
            }
        )
    }
}